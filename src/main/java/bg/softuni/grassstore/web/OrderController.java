package bg.softuni.grassstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @GetMapping("/order-add")
    public String getOrderAdd(){
        return "/order-add";
    }

}
