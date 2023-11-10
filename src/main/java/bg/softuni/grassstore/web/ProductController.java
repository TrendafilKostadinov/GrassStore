package bg.softuni.grassstore.web;

import bg.softuni.grassstore.model.dto.ProductAddDTO;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    private final ModelMapper modelMapper;

    public ProductController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("productAddDTO")
    public ProductAddDTO initProductDTO(){
        return new ProductAddDTO();
    }

    @GetMapping("/product-add")
    public String getAddProduct(){
        return "/product-add";
    }

    @PostMapping("/product-add")
    public String postAddProduct(@Valid ProductAddDTO productAddDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("productAddDTO", productAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddDTO",
                    bindingResult);

            return "redirect:/product-add";
        }

        return "/home";
    }

}
