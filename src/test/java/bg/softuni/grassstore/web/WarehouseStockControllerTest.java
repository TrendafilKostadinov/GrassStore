package bg.softuni.grassstore.web;

import bg.softuni.grassstore.model.dto.ProductAddDTO;
import bg.softuni.grassstore.model.dto.WarehouseStockDTO;
import bg.softuni.grassstore.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class WarehouseStockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;


    @Test
    @WithMockUser(roles = "OSB")
    public void testGetDeliveryAdd() throws Exception {
        mockMvc.perform(get("/delivery-add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("productList"))
                .andExpect(view().name("/delivery-add"));
    }

    @Test
    @WithMockUser(roles = "OSB")
    public void testGetStock() throws Exception {
        mockMvc.perform(get("/stock-get"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("stockAll"))
                .andExpect(view().name("/stock-get"));
    }

    @Test
    @WithMockUser(roles = "OSB")
    public void testPostWarehouseStock() throws Exception {
        Long productId = 1L;
        Long quantity = 10L;

        ProductAddDTO product = new ProductAddDTO().setName("Test").setPrice(BigDecimal.ONE);

        productService.addProduct(product);

        WarehouseStockDTO warehouseStockDTO = new WarehouseStockDTO();
        warehouseStockDTO.setProductId(productId);
        warehouseStockDTO.setQuantity(quantity);

        mockMvc.perform(post("/delivery-add")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("warehouseStockDTO", warehouseStockDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));
    }
}
