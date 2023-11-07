package bg.softuni.grassstore.web;

import bg.softuni.grassstore.model.dto.UserAddDTO;
import bg.softuni.grassstore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userAddDTO")
    public UserAddDTO initUserDTO(){
        return new UserAddDTO();
    }

    @GetMapping("/user-add")
    public String getUserAdd(){
        return "/user-add";
    }

    @PostMapping("/user-add")
    public String postUserAdd(@Valid UserAddDTO userAddDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){
        if(!userService.addUser(userAddDTO)){
            redirectAttributes.addFlashAttribute("userAddDTO", userAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO",
                    bindingResult);

            return "redirect:/user-add";
        }
        return "redirect:/home";
    }
}
