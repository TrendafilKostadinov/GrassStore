package bg.softuni.grassstore.service;

import bg.softuni.grassstore.model.dto.UserAddDTO;
import bg.softuni.grassstore.model.dto.UserDetailDTO;
import bg.softuni.grassstore.model.dto.UserPasswordChangeDTO;
import bg.softuni.grassstore.model.entity.UserEntity;
import bg.softuni.grassstore.model.entity.UserRoleEntity;
import bg.softuni.grassstore.model.enums.RoleNames;
import bg.softuni.grassstore.repository.RolesRepository;
import bg.softuni.grassstore.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RolesRepository rolesRepository;

    private final PasswordEncoder passwordEncoder;

    private final SessionRegistry sessionRegistry;


//    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository,
                       RolesRepository rolesRepository,
                       PasswordEncoder passwordEncoder, SessionRegistry sessionRegistry) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
        this.sessionRegistry = sessionRegistry;
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

    public List<UserDetailDTO> getUsersFromSessionRegistry() {

        List<User> principals = sessionRegistry.getAllPrincipals().stream()
                .filter(u -> !sessionRegistry.getAllSessions(u, false).isEmpty())
                .map(principal -> (User) principal)
                .toList();
        if (principals.size() == 0){
            return new ArrayList<>();
        }

        return principals
                .stream()
                .map(principal ->
                        userRepository
                                .findByEmail(principal.getUsername())
                                .orElse(null))
                .map(this::map).collect(Collectors.toList());
    }

    private UserDetailDTO map(UserEntity userEntity) {
        List<String> roles = userEntity.getRoles()
                .stream()
                .map(r -> r.getName().name())
                .toList();
        return new UserDetailDTO()
                .setEmail(userEntity.getEmail())
                .setFullName(userEntity.getFullName())
                .setId(userEntity.getId())
                .setAllRoles(String.join(",", roles));
    }


    public UserDetailDTO getUser(Long id) {
        UserEntity userEntity = userRepository
                .findById(id)
                .orElse(null);

        if (userEntity == null){
            return null;
        }

       return this.map(userEntity);
    }

    public UserDetailDTO getUser(String email) {
        UserEntity userEntity = userRepository
                .findByEmail(email)
                .orElse(null);
        if (userEntity == null){
            return null;
        }
        return this.map(userEntity);
    }

    public boolean passwordChange(UserPasswordChangeDTO userPasswordChangeDTO, String email) {
        if (!userPasswordChangeDTO.getPassword().equals(userPasswordChangeDTO.getConfirmPassword())){
            return false;
        }

        UserEntity user = userRepository
                .findByEmail(email)
                .get()
                .setPassword(passwordEncoder.encode(userPasswordChangeDTO.getPassword()));
        userRepository.save(user);

        return true;
    }

    public boolean passwordChange(UserPasswordChangeDTO userPasswordChangeDTO, Long id) {
        if (!userPasswordChangeDTO.getPassword().equals(userPasswordChangeDTO.getConfirmPassword())){
            return false;
        }

        UserEntity user = userRepository
                .findById(id)
                .get()
                .setPassword(passwordEncoder.encode(userPasswordChangeDTO.getPassword()));
        userRepository.save(user);

        return true;
    }
}
