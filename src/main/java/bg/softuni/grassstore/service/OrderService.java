package bg.softuni.grassstore.service;

import bg.softuni.grassstore.model.dto.OrderDetailDTO;
import bg.softuni.grassstore.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderService(OrderRepository orderRepository,
                        ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    public List<OrderDetailDTO> getAllActiveOrders() {
        return orderRepository
                .findAllByIsDeliveredIsFalse()
                .stream()
                .map(orderEntity -> modelMapper.map(orderEntity, OrderDetailDTO.class))
                .toList();
    }
}
