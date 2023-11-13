package bg.softuni.grassstore.service;

import bg.softuni.grassstore.model.dto.ProductAddDTO;
import bg.softuni.grassstore.model.dto.ProductDetailDTO;
import bg.softuni.grassstore.model.entity.ProductEntity;
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

    public boolean addProduct(ProductAddDTO productAddDTO) {
        if (productRepository.findByName(productAddDTO.getName()).isPresent()){
            return false;
        }

        ProductEntity productEntity = new ProductEntity()
                .setName(productAddDTO.getName())
                .setPrice(productAddDTO.getPrice())
                .setQuantity(productAddDTO.getQuantity());

        productRepository.save(productEntity);

        return true;
    }

}
