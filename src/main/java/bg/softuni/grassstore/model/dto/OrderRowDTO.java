package bg.softuni.grassstore.model.dto;

public class OrderRowDTO {

    private Long productId;

    private Long quantity;

    public Long getProductId() {
        return productId;
    }

    public OrderRowDTO setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public OrderRowDTO setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }
}
