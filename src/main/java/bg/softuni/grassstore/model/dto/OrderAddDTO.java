package bg.softuni.grassstore.model.dto;

import bg.softuni.grassstore.model.entity.ProductEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class OrderAddDTO {

    @NotNull
    private String customer;

    @NotEmpty
    private List<String> products;

    @Positive
    private Long quantity;

    @NotNull
    private String currency;

    @NotNull
    private boolean isDelivered;

    public String getCustomer() {
        return customer;
    }

    public OrderAddDTO setCustomer(String customer) {
        this.customer = customer;
        return this;
    }

    public List<String> getProducts() {
        return products;
    }

    public OrderAddDTO setProducts(List<String> products) {
        this.products = products;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public OrderAddDTO setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public OrderAddDTO setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public OrderAddDTO setDelivered(boolean delivered) {
        isDelivered = delivered;
        return this;
    }
}
