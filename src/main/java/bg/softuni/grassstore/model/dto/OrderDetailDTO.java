package bg.softuni.grassstore.model.dto;

import bg.softuni.grassstore.model.entity.CurrencyEntity;
import bg.softuni.grassstore.model.entity.CustomerEntity;
import bg.softuni.grassstore.model.entity.ProductEntity;

import java.math.BigDecimal;
import java.util.List;

public class OrderDetailDTO {

    private Long id;

    private CustomerDetailDTO customer;

    private boolean isDelivered;

    private BigDecimal sum;

    public Long getId() {
        return id;
    }

    public OrderDetailDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public CustomerDetailDTO getCustomer() {
        return customer;
    }

    public OrderDetailDTO setCustomer(CustomerDetailDTO customer) {
        this.customer = customer;
        return this;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public OrderDetailDTO setDelivered(boolean delivered) {
        isDelivered = delivered;
        return this;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public OrderDetailDTO setSum(BigDecimal sum) {
        this.sum = sum;
        return this;
    }
}
