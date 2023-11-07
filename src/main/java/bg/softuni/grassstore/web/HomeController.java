package bg.softuni.grassstore.web;

import bg.softuni.grassstore.model.dto.UserDetailDTO;
import bg.softuni.grassstore.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLogin(){
        return "/login";
    }

    @GetMapping("/")
    public String getIndex(){
        return "/login";
    }

    @GetMapping("/home")
    public String getIndex(Model model){
        model.addAttribute( "username",userService.getUserFullName());

        List<UserDetailDTO> users = userService.getUsersFromSessionRegistry();

        return "home";
    }

    @PostMapping("/login-error")
    public String onFailure(
            @ModelAttribute("email") String email,
            Model model) {

        model.addAttribute("email", email);
        model.addAttribute("bad_credentials", "true");

        return "login";
    }
}

