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
import ru.fil.authservice.model.security.CustomUserDetails;
import ru.fil.authservice.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public User findByUsername(String username) {
        return userRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("Неправильный логин или пароль"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        return getUserDetailsFromUserEntity(user);
    }

    private UserDetails getUserDetailsFromUserEntity(User user) {
        return new CustomUserDetails(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().getName())),
                user.getId()
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
