package bg.softuni.grassstore.service;

import bg.softuni.grassstore.model.dto.ProductDetailDTO;
import bg.softuni.grassstore.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public List<ProductDetailDTO> getAllProducts(){
        return productRepository
                .findAll()
                .stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductDetailDTO.class))
                .toList();
    }

    //TODO: add product function
}
