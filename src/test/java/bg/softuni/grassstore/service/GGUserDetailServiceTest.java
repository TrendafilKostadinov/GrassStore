package bg.softuni.grassstore.service;

import bg.softuni.grassstore.model.entity.UserEntity;
import bg.softuni.grassstore.model.entity.UserRoleEntity;
import bg.softuni.grassstore.model.enums.RoleNames;
import bg.softuni.grassstore.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GGUserDetailServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GGUserDetailService userDetailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsername() {
        String userEmail = "test@example.com";
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userEmail);
        userEntity.setPassword("password");
        UserRoleEntity roleEntity = new UserRoleEntity();
        roleEntity.setName(RoleNames.MANAGER);
        userEntity.setRoles(Collections.singletonList(roleEntity));

        when(userRepository.findByEmail(userEmail)).thenReturn(Optional.of(userEntity));

        UserDetails userDetails = userDetailService.loadUserByUsername(userEmail);

        assertNotNull(userDetails);
        assertEquals(userEmail, userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());

        assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER")));
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        String userEmail = "nonexistent@example.com";

        when(userRepository.findByEmail(userEmail)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userDetailService.loadUserByUsername(userEmail));
    }

}

