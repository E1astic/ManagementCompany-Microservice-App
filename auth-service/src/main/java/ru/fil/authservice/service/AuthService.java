package ru.fil.authservice.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fil.authservice.converter.UserConverter;
import ru.fil.authservice.exception.ApartmentNotFoundException;
import ru.fil.authservice.exception.ExistingEmailException;
import ru.fil.authservice.feign.HouseFeignClient;
import ru.fil.authservice.model.dto.JwtRequest;
import ru.fil.authservice.model.dto.UserRegisterRequest;
import ru.fil.authservice.model.entity.User;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;
    private final HouseFeignClient houseFeignClient;


    public String login(JwtRequest jwtRequest) throws BadCredentialsException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                jwtRequest.getEmail(), jwtRequest.getPassword()));

        UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getEmail());
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
            log.info("Выполнен запрос из auth-service в house-service через feign-client");
            User user = userConverter.mapToUser(userRegisterRequest);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(user);
        }
    }
}
