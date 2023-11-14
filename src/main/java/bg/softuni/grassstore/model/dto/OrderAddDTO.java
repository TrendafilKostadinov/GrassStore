package bg.softuni.grassstore.model.dto;

import bg.softuni.grassstore.model.entity.ProductEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;
import java.util.Map;

public class OrderAddDTO {

    @NotNull
    private String customer;

    @NotEmpty
    private List<ProductEntity> productTOSell;

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

    public List<ProductEntity> getProductTOSell() {
        return productTOSell;
    }

    public OrderAddDTO setProductTOSell(List<ProductEntity> productTOSell) {
        this.productTOSell = productTOSell;
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
