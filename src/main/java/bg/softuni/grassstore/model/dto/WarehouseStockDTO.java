package bg.softuni.grassstore.model.dto;

public class WarehouseStockDTO {

    private Long productId;

    private Long quantity;

    public Long getProductId() {
        return productId;
    }

    public WarehouseStockDTO setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public WarehouseStockDTO setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }
}
