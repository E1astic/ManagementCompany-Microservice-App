package ru.fil.authservice.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.fil.authservice.model.dto.UserRegisterRequest;
import ru.fil.authservice.model.entity.User;
import ru.fil.authservice.repository.RoleRepository;

@Component
@RequiredArgsConstructor
public class UserConverter {

    private final ModelMapper modelMapper;

    private final RoleRepository roleRepository;

    public User mapToUser(UserRegisterRequest userRegisterRequest) {
        User user = modelMapper.map(userRegisterRequest, User.class);
        user.setId(null);
        user.setRole(roleRepository.findByName("ROLE_USER").get());
        return user;
    }
}
