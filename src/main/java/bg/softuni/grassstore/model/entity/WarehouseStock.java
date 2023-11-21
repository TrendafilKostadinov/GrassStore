package bg.softuni.grassstore.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "warehouse_stocks")
public class WarehouseStock extends BaseEntity{

    @Positive
    @NotNull
    private Long quantity;

    @OneToOne
    @NotNull
    private ProductEntity product;

    public Long getQuantity() {
        return quantity;
    }

    public WarehouseStock setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public WarehouseStock setProduct(ProductEntity product) {
        this.product = product;
        return this;
    }
}
