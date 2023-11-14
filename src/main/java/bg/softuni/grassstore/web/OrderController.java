package bg.softuni.grassstore.web;

import bg.softuni.grassstore.model.dto.OrderAddDTO;
import bg.softuni.grassstore.model.dto.ProductAddDTO;
import bg.softuni.grassstore.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    private final ProductService productService;

    public OrderController(ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute("orderAddDTO")
    public OrderAddDTO initOrderAddDTO(){
        return new OrderAddDTO();
    }

    @ModelAttribute("productAddDTO")
    public ProductAddDTO initProductAddDTO(){
        return new ProductAddDTO();
    }

    @GetMapping("/order-add")
    public String getOrderAdd(Model model){

        model.addAttribute("productList", productService.getAllProducts());

        return "/order-add";
    }

    @PostMapping("/order-add")
    public String postOrderAdd(@Valid OrderAddDTO orderAddDTO){
        return "/order-add";
    }

}
