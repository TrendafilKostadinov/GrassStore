package bg.softuni.grassstore.model.entity;

import jakarta.persistence.*;

@Table
@Entity(name = "order_rows")
public class OrderRowEntity extends BaseEntity {

    @ManyToOne
    private ProductEntity product;

    private Long quantity;

    @ManyToOne
    @JoinColumn
    private OrderEntity order;

    public ProductEntity getProduct() {
        return product;
    }

    public OrderRowEntity setProduct(ProductEntity product) {
        this.product = product;
        return this;
    }

    public Long getQuantity() {
        return quantity;
    }

    public OrderRowEntity setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public OrderRowEntity setOrder(OrderEntity order) {
        this.order = order;
        return this;
    }
}
