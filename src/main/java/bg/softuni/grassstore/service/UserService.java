package bg.softuni.grassstore.service;

import bg.softuni.grassstore.model.dto.UserAddDTO;
import bg.softuni.grassstore.model.entity.UserEntity;
import bg.softuni.grassstore.model.entity.UserRoleEntity;
import bg.softuni.grassstore.model.enums.RoleNames;
import bg.softuni.grassstore.repository.RolesRepository;
import bg.softuni.grassstore.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RolesRepository rolesRepository;

    private final PasswordEncoder passwordEncoder;

//    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository,
                       RolesRepository rolesRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String getUserFullName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername()).orElse(null);
        if (userEntity != null) {
            return userEntity.getFullName();
        }
        return null;
    }

    public boolean addUser(UserAddDTO userAddDTO) {
        if (!userAddDTO.getPassword().equals(userAddDTO.getConfirmPassword())) {
            return false;
        }

        if (userRepository.findByEmail(userAddDTO.getEmail()).isPresent()) {
            return false;
        }
        UserEntity user =
                new UserEntity()
                .setEmail(userAddDTO.getEmail())
                        .setPassword(passwordEncoder.encode(userAddDTO.getPassword()))
                        .setFullName(userAddDTO.getFullName());

        List<UserRoleEntity> userRoles = new ArrayList<>();

        userAddDTO.getRoles().forEach(role -> {

                    switch (role) {
                        case "MANAGER" -> userRoles.add(rolesRepository.findByName(RoleNames.MANAGER));
                        case "TRADER" -> userRoles.add(rolesRepository.findByName(RoleNames.TRADER));
                        case "OSB" -> userRoles.add(rolesRepository.findByName(RoleNames.OSB));
                    }
                }
        );

        user.setRoles(userRoles);

        userRepository.save(user);

        return true;
    }
}
