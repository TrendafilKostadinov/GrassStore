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

        List<Long> productIdList = requestBody.getProductId();
        List<Long> quantityList = requestBody.getQuantity();

        for (Long qty : quantityList) {
            if (qty == null){
                return ResponseEntity.badRequest().build();
            }
        }

        orderService.addOrder(productIdList, quantityList, customerId);


        return ResponseEntity.ok().build();
    }

}
