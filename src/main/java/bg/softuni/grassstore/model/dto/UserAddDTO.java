package bg.softuni.grassstore.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class UserAddDTO {

    @Email
    @NotNull
    private String email;

    @NotBlank
    @Size(min = 3)
    private String password;

    @NotBlank
    @Size(min = 3)
    private String confirmPassword;

    @Size(min = 3)
    private String fullName;

    @NotNull
    private List<String> roles;

    public String getEmail() {
        return email;
    }

    public UserAddDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserAddDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserAddDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserAddDTO setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public UserAddDTO setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }
}
