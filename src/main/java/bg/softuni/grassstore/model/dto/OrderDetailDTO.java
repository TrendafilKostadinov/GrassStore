package bg.softuni.grassstore.model.dto;

import bg.softuni.grassstore.model.entity.CurrencyEntity;
import bg.softuni.grassstore.model.entity.CustomerEntity;
import bg.softuni.grassstore.model.entity.ProductEntity;

import java.util.List;

public class OrderDetailDTO {

    private CustomerEntity customer;

    private List<ProductEntity> products;

    private CurrencyEntity currency;

    private boolean isDelivered;

    public CustomerEntity getCustomer() {
        return customer;
    }

    public OrderDetailDTO setCustomer(CustomerEntity customer) {
        this.customer = customer;
        return this;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public OrderDetailDTO setProducts(List<ProductEntity> products) {
        this.products = products;
        return this;
    }

    public CurrencyEntity getCurrency() {
        return currency;
    }

    public OrderDetailDTO setCurrency(CurrencyEntity currency) {
        this.currency = currency;
        return this;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public OrderDetailDTO setDelivered(boolean delivered) {
        isDelivered = delivered;
        return this;
    }
}
