package bg.softuni.grassstore.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ProductAddDTO {

    @NotBlank
    @Size(min = 2, max = 30)
    private String name;

    @Min(0)
    @NotNull
    private BigDecimal price;

    @NotNull
    @Positive
    private Double quantity;

    public String getName() {
        return name;
    }

    public ProductAddDTO setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductAddDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public ProductAddDTO setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }
}
