package ru.fil.authservice.converter;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.fil.authservice.model.dto.UserApplicationDto;
import ru.fil.authservice.model.dto.UserRegisterRequest;
import ru.fil.authservice.model.entity.Role;
import ru.fil.authservice.model.entity.User;
import ru.fil.authservice.repository.RoleRepository;

@Component
@RequiredArgsConstructor
public class UserConverter {

    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private Role userRole;

    @PostConstruct
    public void initUserRole() {
        this.userRole = roleRepository.findByName("ROLE_USER").get();
    }

    public User mapToUser(UserRegisterRequest userRegisterRequest) {
        User user = modelMapper.map(userRegisterRequest, User.class);
        user.setId(null);
        user.setRole(userRole);
        return user;
    }

    public UserApplicationDto mapToUserApplicationDto(User user) {
        return modelMapper.map(user, UserApplicationDto.class);
    }
}
