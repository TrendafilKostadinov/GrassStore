package bg.softuni.grassstore.model.dto;

import jakarta.validation.constraints.Positive;

import java.util.List;

public class OrderAddDTO {

    private List<Long> productId;

    @Positive
    private List<Long> quantity;

    public List<Long> getProductId() {
        return productId;
    }

    public OrderAddDTO setProductId(List<Long> productId) {
        this.productId = productId;
        return this;
    }

    public List<Long> getQuantity() {
        return quantity;
    }

    public OrderAddDTO setQuantity(List<Long> quantity) {
        this.quantity = quantity;
        return this;
    }
}
