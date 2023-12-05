package bg.softuni.grassstore.web;

import bg.softuni.grassstore.model.dto.UserAddDTO;
import bg.softuni.grassstore.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        UserAddDTO userAddDTO = new UserAddDTO();
        userAddDTO.setEmail("test1@user.com")
                .setPassword("testpassword")
                .setConfirmPassword("testpassword")
                .setFullName("TEST")
                .setRoles(List.of("OSB"));

        userService.addUser(userAddDTO);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetUserAdd() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user-add"))
                .andExpect(status().isOk())
                .andExpect(view().name("/user-add"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testPostUserAdd() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user-add")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("email", "test@user.com")
                        .param("password", "testpassword")
                        .param("confirmPassword", "testpassword")
                        .param("fullName", "TEST")
                        .param("roles", "OSB"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/home"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testPostUserDelete() throws Exception {
        Long userId = userService.getUser("test1@user.com").getId();
        mockMvc.perform(MockMvcRequestBuilders.post("/user-delete/{id}", userId)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetUserPassword() throws Exception {
        Long userId = userService.getUser("test1@user.com").getId();
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/user-password/{id}", userId)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/user-password"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("userToChange"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("userPassChange"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testPostUserPasswordBadCredentials() throws Exception {
        Long userId = userService.getUser("test1@user.com").getId();
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/user-password/{id}", userId)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("password", "n")
                        .param("confirmPassword", "n"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.flash().attributeExists("bad_credentials"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testPostUserPasswordDiffPasswords() throws Exception {
        Long userId = userService.getUser("test1@user.com").getId();
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/user-password/{id}", userId)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("password", "newPass")
                        .param("confirmPassword", "diffNewPass"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.flash().attributeExists("diff_passwords"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetUserDetail() throws Exception {
        Long userId = userService.getUser("test1@user.com").getId();
        mockMvc.perform(MockMvcRequestBuilders.get("/user-detail/{id}", userId)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("/user-detail"))
                .andExpect(model().attributeExists("user"));
    }


}
