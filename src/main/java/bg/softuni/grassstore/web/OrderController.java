package bg.softuni.grassstore.web;

import bg.softuni.grassstore.model.dto.OrderAddDTO;
import bg.softuni.grassstore.model.dto.ProductAddDTO;
import bg.softuni.grassstore.service.CustomerService;
import bg.softuni.grassstore.service.OrderService;
import bg.softuni.grassstore.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderController {

    private final ProductService productService;
    private final CustomerService customerService;
    private final OrderService orderService;

    public OrderController(ProductService productService,
                           CustomerService customerService,
                           OrderService orderService) {
        this.productService = productService;
        this.customerService = customerService;
        this.orderService = orderService;
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

    @PostMapping("/order-ship/{id}")
    public String shipOrder(@PathVariable Long id,
                            RedirectAttributes redirectAttributes){

        if(orderService.shipOrder(id)){

            redirectAttributes.addFlashAttribute("shipped", true);

            return "redirect:/home";
        }

        redirectAttributes.addFlashAttribute("lowQuantity", true);

        return "redirect:/home";
    }

}
