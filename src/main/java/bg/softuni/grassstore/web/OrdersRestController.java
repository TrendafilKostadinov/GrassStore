package bg.softuni.grassstore.web;

import bg.softuni.grassstore.model.dto.OrderDetailDTO;
import bg.softuni.grassstore.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrdersRestController {

    private final OrderService orderService;

    public OrdersRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders/all")
    public ResponseEntity<List<OrderDetailDTO>> getAllUsers(){
        return ResponseEntity.ok(orderService.getAllActiveOrders());
    }

}
