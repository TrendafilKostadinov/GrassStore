package bg.softuni.grassstore.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void testGetAddCustomer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/customer-add"))
                .andExpect(status().isOk())
                .andExpect(view().name("/customer-add"))
                .andExpect(model().attributeExists("allAddresses"));
    }

    @Test
    @WithMockUser(roles = "TRADER")
    public void testPostAddCustomer_ExistingCustomer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/customer-add")
                        .with(csrf())
                        .param("someField", "someValue")
                        .param("existingCustomer", "true"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/customer-add"));
    }

    @Test
    @WithMockUser(roles = "TRADER")
    public void testDeleteCustomer_Success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/customer-delete/{id}", 1L)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }
}

