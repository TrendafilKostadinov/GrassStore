package bg.softuni.grassstore.model.dto;

import jakarta.validation.constraints.Positive;

public class OrderAddDTO {

    private Long productId;

    @Positive
    private int quantity;

    public Long getProductId() {
        return productId;
    }

    public OrderAddDTO setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderAddDTO setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }
}
