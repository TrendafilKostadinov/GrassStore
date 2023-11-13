package bg.softuni.grassstore.model.dto;

import bg.softuni.grassstore.model.entity.ProductEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class OrderAddDTO {

    @NotNull
    private String customer;

    @NotEmpty
    private List<ProductEntity> products;

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

    public List<ProductEntity> getProducts() {
        return products;
    }

    public OrderAddDTO setProducts(List<ProductEntity> products) {
        this.products = products;
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