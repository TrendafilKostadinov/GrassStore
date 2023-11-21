package bg.softuni.grassstore.service;

import bg.softuni.grassstore.model.dto.OrderDetailDTO;
import bg.softuni.grassstore.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllActiveOrders() {

        when(orderRepository.findAllByIsDeliveredIsFalse()).thenReturn(Collections.emptyList());

        when(modelMapper.map(null, OrderDetailDTO.class)).thenReturn(null);

        List<OrderDetailDTO> result = orderService.getAllActiveOrders();

        assertEquals(Collections.emptyList(), result);

    }
}