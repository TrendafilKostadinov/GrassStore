package bg.softuni.grassstore.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity{

    @ManyToOne
    @NotNull
    private CustomerEntity customer;

    @NotEmpty
    @OneToMany(mappedBy = "orderId")
    private List<OrderRowEntity> products;

    private boolean isDelivered = false;

    public CustomerEntity getCustomer() {
        return customer;
    }

    public OrderEntity setCustomer(CustomerEntity customer) {
        this.customer = customer;
        return this;
    }

    public List<OrderRowEntity> getProducts() {
        return products;
    }

    public OrderEntity setProducts(List<OrderRowEntity> products) {
        this.products = products;
        return this;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public OrderEntity setDelivered(boolean delivered) {
        isDelivered = delivered;
        return this;
    }
}
