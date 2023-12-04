package bg.softuni.grassstore.web;

import bg.softuni.grassstore.model.dto.ProductAddDTO;
import bg.softuni.grassstore.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Test
    @WithMockUser(roles = "OSB")
    public void testGetAddProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product-add"))
                .andExpect(status().isOk())
                .andExpect(view().name("/product-add"));
    }

    @Test
    @WithMockUser(roles = "OSB")
    public void testPostAddProduct() throws Exception {

        ProductAddDTO productAddDTO = new ProductAddDTO();
        productAddDTO.setName("TEST").setPrice(BigDecimal.TEN);


        mockMvc.perform(MockMvcRequestBuilders.post("/product-add")
                        .with(csrf())
                        .flashAttr("productAddDTO", productAddDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }

    @Test
    @WithMockUser(roles = "OSB")
    public void testPostAddProductValidationError() throws Exception {
        // Mock data
        ProductAddDTO productAddDTO = new ProductAddDTO();

        mockMvc.perform(MockMvcRequestBuilders.post("/product-add")
                        .with(csrf())
                        .flashAttr("productAddDTO", productAddDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product-add"))
                .andExpect(flash().attributeExists("productAddDTO", "org.springframework.validation.BindingResult.productAddDTO"));
    }
}

