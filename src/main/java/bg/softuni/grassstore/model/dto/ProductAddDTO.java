package bg.softuni.grassstore.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ProductAddDTO {

    @NotBlank
    @Size(min = 2, max = 30)
    private String name;

    @Min(0)
    @NotNull
    private BigDecimal price;

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
}
