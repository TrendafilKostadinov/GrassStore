package bg.softuni.grassstore.service;

import bg.softuni.grassstore.model.dto.ProductAddDTO;
import bg.softuni.grassstore.model.dto.ProductDetailDTO;
import bg.softuni.grassstore.model.entity.ProductEntity;
import bg.softuni.grassstore.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts() {
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        when(modelMapper.map(null, ProductDetailDTO.class)).thenReturn(null);

        List<ProductDetailDTO> result = productService.getAllProducts();

        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void addProduct() {

        when(productRepository.findByName("existingProduct")).thenReturn(Optional.of(new ProductEntity()));

        when(modelMapper.map(null, ProductDetailDTO.class)).thenReturn(null);

        ProductAddDTO existingProductDTO = new ProductAddDTO()
                .setName("existingProduct")
                .setPrice(BigDecimal.TEN)
                .setQuantity(20.0);
        assertFalse(productService.addProduct(existingProductDTO));

        ProductAddDTO newProductDTO = new ProductAddDTO()
                .setName("newProduct")
                .setPrice(BigDecimal.valueOf(15.99))
                .setQuantity(10.0);
        assertTrue(productService.addProduct(newProductDTO));


    }
}