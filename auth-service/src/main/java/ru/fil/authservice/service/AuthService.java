package ru.fil.authservice.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.fil.authservice.converter.UserConverter;
import ru.fil.authservice.exception.ApartmentNotFoundException;
import ru.fil.authservice.exception.ExistingEmailException;
import ru.fil.authservice.feign.AddressFeignClient;
import ru.fil.authservice.model.dto.JwtRequest;
import ru.fil.authservice.model.dto.UserRegisterRequest;
import ru.fil.authservice.model.entity.User;
import ru.fil.authservice.model.security.CustomUserDetails;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;
    private final AddressFeignClient houseFeignClient;


    public String login(JwtRequest jwtRequest) throws BadCredentialsException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                jwtRequest.getEmail(), jwtRequest.getPassword()));
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return jwtService.generateToken(userDetails);
    }

    public void register(UserRegisterRequest userRegisterRequest) {
        try {
            userService.loadUserByUsername(userRegisterRequest.getEmail());
            throw new ExistingEmailException();
        } catch(UsernameNotFoundException e) {
            try {
                houseFeignClient.getApartmentById(userRegisterRequest.getApartmentId());
            } catch(FeignException ex) {
                if(ex.status() == HttpStatus.NOT_FOUND.value()) {
                    throw new ApartmentNotFoundException();
                }
            }
            log.info("Request from AUTH-SERVICE to HOUSE-SERVICE by feign-client");
            User user = userConverter.mapToUser(userRegisterRequest);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(user);
        }
    }
}
