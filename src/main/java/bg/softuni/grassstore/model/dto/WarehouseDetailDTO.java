package bg.softuni.grassstore.model.dto;

public class WarehouseDetailDTO {

    private Long quantity;
    private String product;

    public Long getQuantity() {
        return quantity;
    }

    public WarehouseDetailDTO setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getProduct() {
        return product;
    }

    public WarehouseDetailDTO setProduct(String product) {
        this.product = product;
        return this;
    }
}
