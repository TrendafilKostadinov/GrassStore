package bg.softuni.grassstore.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetUserAdd() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user-add"))
                .andExpect(status().isOk())
                .andExpect(view().name("/user-add"));
    }
    //TODO: More tests
    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetUserDetail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user-detail/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("/user-detail"))
                .andExpect(model().attributeExists("user"));
    }


}
