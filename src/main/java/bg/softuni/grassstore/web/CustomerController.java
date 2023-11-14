package bg.softuni.grassstore.web;

import bg.softuni.grassstore.model.dto.CustomerAddDTO;
import bg.softuni.grassstore.service.AddressService;
import bg.softuni.grassstore.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CustomerController {

    private final AddressService addressService;
    private final CustomerService customerService;

    public CustomerController(AddressService addressService,
                              CustomerService customerService) {
        this.addressService = addressService;
        this.customerService = customerService;
    }

    @ModelAttribute("customerAddDTO")
    public CustomerAddDTO initCustomerDTO(){
        return new CustomerAddDTO();
    }

    @GetMapping("/customer-add")
    public String getAddCustomer(Model model){

        model.addAttribute("allAddresses", addressService.getAllAddresses());

        return "/customer-add";
    }

    @PostMapping("/customer-add")
    public String postAddCustomer(@Valid CustomerAddDTO customerAddDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("customerAddDTO", customerAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.customerAddDTO",
                    bindingResult);

            return "redirect:/customer-add";
        }

        if (!customerService.addCustomer(customerAddDTO)){

            redirectAttributes.addFlashAttribute("existingCustomer", true);

            return "redirect:/customer-add";
        }


        return "redirect:/home";
    }
}
