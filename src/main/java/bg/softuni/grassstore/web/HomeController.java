package bg.softuni.grassstore.web;

import bg.softuni.grassstore.model.dto.CustomerDetailDTO;
import bg.softuni.grassstore.model.dto.UserDetailDTO;
import bg.softuni.grassstore.service.CustomerService;
import bg.softuni.grassstore.service.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class HomeController {

    private final UserService userService;
    private final CustomerService customerService;

    public HomeController(UserService userService,
                          CustomerService customerService) {
        this.userService = userService;
        this.customerService = customerService;
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
        List<UserDetailDTO> users = userService.getUsersFromSessionRegistry();
        List<CustomerDetailDTO> customers = customerService.getAllCustomersByTrader();

        model.addAttribute( "username",userService.getUserFullName());
        model.addAttribute("users", users);
        model.addAttribute("customers", customers);

        return "home";
    }

    @GetMapping("/all-users")
    public String getAllUsers(Model model){
        List<UserDetailDTO> users = userService.getAllUsers();

        model.addAttribute("users", users);

        return "all-users";
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

