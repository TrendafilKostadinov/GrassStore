package bg.softuni.grassstore.service;

import bg.softuni.grassstore.model.dto.OrderDetailDTO;
import bg.softuni.grassstore.model.entity.OrderEntity;
import bg.softuni.grassstore.model.entity.OrderRowEntity;
import bg.softuni.grassstore.repository.CustomerRepository;
import bg.softuni.grassstore.repository.OrderRepository;
import bg.softuni.grassstore.repository.OrderRowRepository;
import bg.softuni.grassstore.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final OrderRowRepository orderRowRepository;

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository,
                        OrderRowRepository orderRowRepository,
                        CustomerRepository customerRepository,
                        ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderRowRepository = orderRowRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    public List<OrderDetailDTO> getAllActiveOrders() {
        return orderRepository
                .findAllByIsDeliveredIsFalse()
                .stream()
                .map(orderEntity -> modelMapper.map(orderEntity, OrderDetailDTO.class))
                .toList();
    }

    public boolean addOrder(List<Long> productIdList, List<Long> quantityList, Long customerId) {

        if (productIdList.size() != quantityList.size()){
            return false;
        }

        List<OrderRowEntity> rowList = new ArrayList<>();
        for (int i = 0; i < productIdList.size(); i++) {
            OrderRowEntity row = new OrderRowEntity();
            row.setProduct(productRepository.findById(productIdList.get(i)).orElseThrow());
            row.setQuantity(quantityList.get(i));

            rowList.add(orderRowRepository.save(row));
        }

        OrderEntity order = new OrderEntity();

        order.setCustomer(customerRepository.findById(customerId).orElseThrow());

        order.setProducts(rowList);

        OrderEntity orderToSave = orderRepository.save(order);

        for (OrderRowEntity row : rowList) {
            row.setOrder(orderToSave);
        }

        orderRowRepository.saveAll(rowList);

        return true;
    }

    public BigDecimal getOrderSum(Long orderId){

        List<OrderRowEntity> allOrderRows = orderRowRepository.findAllByOrderId(orderId);

        if (allOrderRows == null){
            return BigDecimal.ZERO;
        }

        BigDecimal sum = BigDecimal.ZERO;

        for (OrderRowEntity row : allOrderRows) {
            BigDecimal price = row.getProduct().getPrice();
            Long quantity = row.getQuantity();
            sum = sum.add(price.multiply(BigDecimal.valueOf(quantity)));
        }

        return sum;
    }

    public List<OrderDetailDTO> calculateAllSum(List<OrderDetailDTO> orders) {
        return orders
                .stream()
                .map(order -> order.setSum(this.getOrderSum(order.getId())))
                .toList();
    }
}
