package bg.softuni.grassstore.service;

import bg.softuni.grassstore.model.dto.UserAddDTO;
import bg.softuni.grassstore.model.dto.UserDetailDTO;
import bg.softuni.grassstore.model.entity.UserEntity;
import bg.softuni.grassstore.model.entity.UserRoleEntity;
import bg.softuni.grassstore.model.enums.RoleNames;
import bg.softuni.grassstore.repository.RolesRepository;
import bg.softuni.grassstore.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RolesRepository rolesRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private SessionRegistry sessionRegistry;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        when(modelMapper.map(any(), any())).thenReturn(new UserDetailDTO());

    }

    @Test
    void testAddUser_Success() {
        UserAddDTO userAddDTO = new UserAddDTO();
        userAddDTO.setEmail("john@example.com");
        userAddDTO.setPassword("password");
        userAddDTO.setConfirmPassword("password");
        userAddDTO.setFullName("John Doe");
        userAddDTO.setRoles(Collections.singletonList("TRADER"));

        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.empty());
        when(rolesRepository.findByName(RoleNames.TRADER)).thenReturn(new UserRoleEntity());

        boolean result = userService.addUser(userAddDTO);

        assertTrue(result);
        verify(userRepository).save(any());
    }

    @Test
    void testAddUser_Failure_PasswordMismatch() {
        UserAddDTO userAddDTO = new UserAddDTO();
        userAddDTO.setPassword("password");
        userAddDTO.setConfirmPassword("mismatch");

        boolean result = userService.addUser(userAddDTO);

        assertFalse(result);
        verify(userRepository, never()).save(any());
    }

    @Test
    void testAddUser() {
        UserAddDTO userAddDTO = new UserAddDTO();
        userAddDTO.setEmail("test@example.com");
        userAddDTO.setPassword("password");
        userAddDTO.setConfirmPassword("password");
        userAddDTO.setFullName("Test User");
        userAddDTO.setRoles(Collections.singletonList("TRADER"));

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
        when(rolesRepository.findByName(RoleNames.TRADER)).thenReturn(new UserRoleEntity());

        assertTrue(userService.addUser(userAddDTO));

        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void testAddUser_PasswordMismatch() {
        UserAddDTO userAddDTO = new UserAddDTO();
        userAddDTO.setEmail("test@example.com");
        userAddDTO.setPassword("password");
        userAddDTO.setConfirmPassword("differentpassword");
        userAddDTO.setFullName("Test User");
        userAddDTO.setRoles(Collections.singletonList("TRADER"));

        assertFalse(userService.addUser(userAddDTO));
        verify(userRepository, never()).save(any());
    }

    @Test
    void testAddUser_EmailExists() {
        UserAddDTO userAddDTO = new UserAddDTO();
        userAddDTO.setEmail("test@example.com");
        userAddDTO.setPassword("password");
        userAddDTO.setConfirmPassword("password");
        userAddDTO.setFullName("Test User");
        userAddDTO.setRoles(Collections.singletonList("TRADER"));

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(new UserEntity()));

        assertFalse(userService.addUser(userAddDTO));
        verify(userRepository, never()).save(any());
    }

}
