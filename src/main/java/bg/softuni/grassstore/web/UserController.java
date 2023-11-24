package bg.softuni.grassstore.web;

import bg.softuni.grassstore.model.dto.UserAddDTO;
import bg.softuni.grassstore.model.dto.UserDetailDTO;
import bg.softuni.grassstore.model.dto.UserPasswordChangeDTO;
import bg.softuni.grassstore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

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

    @ModelAttribute("userDetailDTO")
    public UserDetailDTO initUserDetailDTO(){
        return new UserDetailDTO();
    }

    @GetMapping("/user-add")
    public String getUserAdd(){
        return "/user-add";
    }

    @PostMapping("/user-add")
    public String postUserAdd(@Valid UserAddDTO userAddDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){
        if(!userService.addUser(userAddDTO) ||
                bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userAddDTO", userAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userAddDTO",
                    bindingResult);

            return "redirect:/user-add";
        }
        return "redirect:/home";
    }

    @GetMapping("/user-detail/{id}")
    public String getUserDetail(@PathVariable Long id,
                                Model model){

        UserDetailDTO user = userService.getUser(id);
        model.addAttribute("user", user);

        return "/user-detail";
    }

    @GetMapping("/admin/user-password/{id}")
    public String getUserPassword(@PathVariable Long id,
                                  Model model){
        model.addAttribute("userToChange", userService.getUser(id));
        model.addAttribute("userPassChange", new UserPasswordChangeDTO());

        return "/user-password";
    }

    @PostMapping("/admin/user-password/{id}")
    public String postUserPassword(@PathVariable Long id,
                                  @Valid UserPasswordChangeDTO userPasswordChangeDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes,
                                  Model model){
        model.addAttribute("userToChange", userService.getUser(id));
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("bad_credentials", true);

            return "redirect:/admin/user-password/" + id;
        }

        if (!userService.passwordChange(userPasswordChangeDTO, id)){
            redirectAttributes.addFlashAttribute("diff_passwords", true);

            return "redirect:/admin/user-password/" + id;
        }
        redirectAttributes.addFlashAttribute("good_credentials", true);

        return "redirect:/admin/user-password/" + id;
    }

    @GetMapping("/user-password")
    public String getUserPassword(Model model){

        model.addAttribute("userPassChange", new UserPasswordChangeDTO());

        return "/user-password";
    }

    @PostMapping("/user-password")
    public String getUserPassword(@Valid UserPasswordChangeDTO userPasswordChangeDTO,
                                    BindingResult bindingResult,
                                    Principal principal,
                                    Model model){
        model.addAttribute("userPassChange", userPasswordChangeDTO);
        if (bindingResult.hasErrors()){
            model.addAttribute("bad_credentials", true);

            return "/user-password";
        }
        if (!userService.passwordChange(userPasswordChangeDTO, principal.getName())) {
            model.addAttribute("diff_passwords", true);

            return "/user-password";
        }

        model.addAttribute("good_credentials", true);

        return "/user-password";
    }

    @PostMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable Long id){

        userService.deleteUser(id);

        return "redirect:/home";
    }

    @PostMapping("/user-change-name/{id}")
    public String changeFullName(@PathVariable Long id,
                                 UserDetailDTO userDetailDTO){

            if (!userDetailDTO.getFullName().isBlank()){
                userService.changeUserFullName(id, userDetailDTO.getFullName());
            }

            return "redirect:/user-detail/" + id;
    }

    @PostMapping("/admin/user-terminate/{id}")
    public String terminateSession(@PathVariable Long id){

        userService.terminateSession(id);

        return "redirect:/home";
    }

    @GetMapping("/change-role")
    public String getChangeRole(Model model){

        List<UserDetailDTO> users = userService.getAllUsers();

        model.addAttribute("users", users);

        return "/change-role";
    }

    @GetMapping("/change-role/{id}")
    public String getUserRole(@PathVariable Long id,
                                Model model){

        UserDetailDTO user = userService.getUser(id);
        model.addAttribute("user", user);

        return "/user-detail";
    }

    @PostMapping("/change-role/{id}")
    public String postUserRole(@PathVariable Long id,
                              String btnradio){

        boolean changed = userService.changeUserRole(id, btnradio);

        return "redirect:/change-role";
    }
}
