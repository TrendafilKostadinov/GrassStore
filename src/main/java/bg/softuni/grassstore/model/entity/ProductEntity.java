package bg.softuni.grassstore.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity{

    @NotBlank
    @Size(min = 2, max = 30)
    @Column(unique = true)
    private String name;

    @Min(0)
    @NotNull
    private BigDecimal price;

    @Positive
    @NotNull
    private Double quantity;

    public String getName() {
        return name;
    }

    public ProductEntity setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public ProductEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }
}
