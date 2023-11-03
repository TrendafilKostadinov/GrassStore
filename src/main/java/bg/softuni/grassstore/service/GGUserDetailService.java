package bg.softuni.grassstore.service;

import bg.softuni.grassstore.model.entity.UserEntity;
import bg.softuni.grassstore.model.entity.UsersRolesEntity;
import bg.softuni.grassstore.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class GGUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public GGUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .map(GGUserDetailService::map)
                .orElseThrow(()->new UsernameNotFoundException("User with email " + email + " not found!"));
    }

    private static UserDetails map(UserEntity userEntity) {
        return User
                .withUsername(userEntity.getEmail())
                .password(userEntity.getPassword())
                .authorities(userEntity.getRoles().stream().map(GGUserDetailService::map).toList())
                .build();
    }

    private static GrantedAuthority map(UsersRolesEntity usersRolesEntity) {
        return new SimpleGrantedAuthority(
                "ROLE_" + usersRolesEntity.getName().name()
        );
    }
}
