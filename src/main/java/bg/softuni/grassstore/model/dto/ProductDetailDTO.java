package bg.softuni.grassstore.model.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ProductDetailDTO {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 30)
    private String name;

    @Min(0)
    @NotNull
    private BigDecimal price;

    @Positive
    @NotNull
    private Double quantity;

    public Long getId() {
        return id;
    }

    public ProductDetailDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDetailDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductDetailDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public ProductDetailDTO setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }
}
