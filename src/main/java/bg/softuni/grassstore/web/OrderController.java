package bg.softuni.grassstore.web;

import bg.softuni.grassstore.model.dto.OrderAddDTO;
import bg.softuni.grassstore.model.dto.ProductAddDTO;
import bg.softuni.grassstore.service.CustomerService;
import bg.softuni.grassstore.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderController {

    private final ProductService productService;
    private final CustomerService customerService;

    public OrderController(ProductService productService,
                           CustomerService customerService) {
        this.productService = productService;
        this.customerService = customerService;
    }

    @ModelAttribute("orderAddDTO")
    public OrderAddDTO initOrderAddDTO(){
        return new OrderAddDTO();
    }

    @ModelAttribute("productAddDTO")
    public ProductAddDTO initProductAddDTO(){
        return new ProductAddDTO();
    }

    @GetMapping("/order-add/{customerId}")
    public String getOrderAdd(@PathVariable Long customerId,
            Model model){

        model.addAttribute("productList", productService.getAllProducts());
        model.addAttribute("customer", customerService.getCustomer(customerId));

        return "/order-add";
    }

//    @PostMapping("/order-add/{customerId}")
//    public String postOrderAdd(@PathVariable Long customerId,
//            List<OrderAddDTO> orderAddDTO){
//        return "/home";
//    }

}
