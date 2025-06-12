package ru.fil.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.fil.authservice.converter.UserConverter;
import ru.fil.authservice.exception.ExistingEmailException;
import ru.fil.authservice.model.dto.JwtRequest;
import ru.fil.authservice.model.dto.UserRegisterRequest;
import ru.fil.authservice.model.entity.User;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;


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
            User user = userConverter.mapToUser(userRegisterRequest);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(user);
        }
    }
}
