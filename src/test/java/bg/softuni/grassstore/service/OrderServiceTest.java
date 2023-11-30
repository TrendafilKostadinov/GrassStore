package bg.softuni.grassstore.service;

import bg.softuni.grassstore.model.dto.OrderDetailDTO;
import bg.softuni.grassstore.model.entity.CustomerEntity;
import bg.softuni.grassstore.model.entity.OrderEntity;
import bg.softuni.grassstore.model.entity.OrderRowEntity;
import bg.softuni.grassstore.model.entity.ProductEntity;
import bg.softuni.grassstore.repository.CustomerRepository;
import bg.softuni.grassstore.repository.OrderRepository;
import bg.softuni.grassstore.repository.OrderRowRepository;
import bg.softuni.grassstore.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderRowRepository orderRowRepository;

    @Mock
    private WarehouseStockService warehouseStockService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserService userService;

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

    @Test
    void testAddOrder_Successful() {
        Long productId1 = 1L;
        Long productId2 = 2L;
        List<Long> productIdList = Arrays.asList(productId1, productId2);
        List<Long> quantityList = Arrays.asList(3L, 4L);
        Long customerId = 123L;

        OrderRowEntity orderRow1 = new OrderRowEntity().setProduct((ProductEntity) new ProductEntity().setId(productId1)).setQuantity(3L);
        OrderRowEntity orderRow2 = new OrderRowEntity().setProduct((ProductEntity) new ProductEntity().setId(productId2)).setQuantity(4L);
        List<OrderRowEntity> orderRowList = Arrays.asList(orderRow1, orderRow2);

        OrderEntity orderEntity = (OrderEntity) new OrderEntity().setId(456L);
        orderEntity.setProducts(orderRowList);

        when(productRepository.findById(productId1)).thenReturn(Optional.of(new ProductEntity()));
        when(productRepository.findById(productId2)).thenReturn(Optional.of(new ProductEntity()));
        when(orderRowRepository.save(any(OrderRowEntity.class))).thenReturn(orderRow1, orderRow2);
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(new CustomerEntity()));

        boolean result = orderService.addOrder(productIdList, quantityList, customerId);

        assertTrue(result);
        verify(productRepository, times(2)).findById(anyLong());
        verify(orderRowRepository, times(2)).save(any(OrderRowEntity.class));
        verify(orderRepository, times(1)).save(any(OrderEntity.class));
        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    void testAddOrder_Unsuccessful_QuantityMismatch() {
        List<Long> productIdList = Arrays.asList(1L, 2L);
        List<Long> quantityList = Arrays.asList(3L);

        boolean result = orderService.addOrder(productIdList, quantityList, 123L);

        assertFalse(result);
        verifyNoInteractions(productRepository);
        verifyNoInteractions(orderRowRepository);
        verifyNoInteractions(orderRepository);
        verifyNoInteractions(customerRepository);
        verifyNoInteractions(warehouseStockService);
    }

    @Test
    void testCalculateAllSum() {

        OrderDetailDTO order1 = new OrderDetailDTO().setId(1L);
        OrderDetailDTO order2 = new OrderDetailDTO().setId(2L);
        List<OrderDetailDTO> orders = Arrays.asList(order1, order2);

        List<OrderDetailDTO> result = orderService.calculateAllSum(orders);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(BigDecimal.ZERO, order1.getSum());
        assertEquals(BigDecimal.ZERO, order2.getSum());
    }
}