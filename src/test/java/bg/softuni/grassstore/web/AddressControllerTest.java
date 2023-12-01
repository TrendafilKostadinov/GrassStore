package bg.softuni.grassstore.web;

import bg.softuni.grassstore.model.dto.AddressDetailDTO;
import bg.softuni.grassstore.service.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @Test
    @WithMockUser(roles = "TRADER")
    public void testGetAddressAdd() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/address-add"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/address-add"));
    }

    @Test
    @WithMockUser(roles = "TRADER")
    public void testPostAddressAdd_Success() throws Exception {
        mockMvc.perform(post("/address-add")
                        .with(csrf())
                        .param("street", "Sample Street")
                        .param("city", "Sample City")
                        .param("country", "Sample Country"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/customer-add"));

        verify(addressService).addAddress(any(AddressDetailDTO.class));
    }

    @Test
    @WithMockUser(roles = "TRADER")
    public void testPostAddressAdd_ValidationFailure() throws Exception {
        AddressDetailDTO addressAddDTO = new AddressDetailDTO();

        mockMvc.perform(post("/address-add")
                        .with(csrf())
                        .flashAttr("addressAddDTO", addressAddDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/address-add"))
                .andExpect(MockMvcResultMatchers.flash().attributeExists("addressAddDTO"))
                .andExpect(MockMvcResultMatchers.flash().attributeExists("org.springframework.validation.BindingResult.addressAddDTO"));

        verifyNoInteractions(addressService);
    }
}

