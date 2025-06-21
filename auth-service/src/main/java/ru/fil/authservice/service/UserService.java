package ru.fil.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fil.authservice.converter.UserConverter;
import ru.fil.authservice.model.dto.UserApplicationDto;
import ru.fil.authservice.model.entity.User;
import ru.fil.authservice.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public User findByUsername(String username) {
        return userRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("Пользователь с email %s не найден", username)));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        return getUserDetailsFromUserEntity(user);
    }

    public UserDetails getUserDetailsFromUserEntity(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().getName()))
        );
    }

    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<UserApplicationDto> getAllUserApplicationDto(List<Integer> userIds) {
        return userRepository.findByIdIn(userIds)
                .stream()
                .map(userConverter::mapToUserApplicationDto)
                .toList();
    }
}
