package bg.softuni.grassstore.web;

import bg.softuni.grassstore.model.dto.OrderAddDTO;
import bg.softuni.grassstore.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class OrdersRestController {

    private final OrderService orderService;

    @ModelAttribute("orderAddDTO")
    public OrderAddDTO initOrderDTO() {
        return new OrderAddDTO();
    }

    public OrdersRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "/order-add/{customerId}")
    public ResponseEntity<String> postOrder(@ModelAttribute OrderAddDTO requestBody,
                                           @PathVariable Long customerId) {

        orderService.addOrder(requestBody.getProductId(), requestBody.getQuantity(), customerId);


        return ResponseEntity.ok().build();
    }

}
