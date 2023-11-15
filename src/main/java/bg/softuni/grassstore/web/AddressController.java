package bg.softuni.grassstore.web;

import bg.softuni.grassstore.model.dto.AddressDetailDTO;
import bg.softuni.grassstore.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @ModelAttribute("addressAddDTO")
    public AddressDetailDTO initAddressDTO(){
        return new AddressDetailDTO();
    }

    @GetMapping("/address-add")
    public String getAddressAdd(){
        return "/address-add";
    }

    @PostMapping("/address-add")
    public String postAddressAdd(@Valid AddressDetailDTO addressAddDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addressAddDTO", addressAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addressAddDTO",
                    bindingResult);

            return "redirect:/address-add";
        }

        addressService.addAddress(addressAddDTO);

        return "redirect:/customer-add";
    }
}
