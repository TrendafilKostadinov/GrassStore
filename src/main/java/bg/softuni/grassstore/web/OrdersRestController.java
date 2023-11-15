package bg.softuni.grassstore.web;

import bg.softuni.grassstore.model.dto.OrderAddDTO;
import bg.softuni.grassstore.model.dto.OrderDetailDTO;
import bg.softuni.grassstore.service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrdersRestController {

    private final OrderService orderService;
    @ModelAttribute("orderAddDTO")
    public OrderAddDTO initOrderDTO(){
        return new OrderAddDTO();
    }

    public OrdersRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path="/order-add/{customerId}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderAddDTO> getOrder(@PathVariable Long customerId,
                                      @RequestBody List<OrderAddDTO> orders){
        return null;
    }

}
