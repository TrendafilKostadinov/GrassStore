package bg.softuni.grassstore.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserPasswordChangeDTO {

    @NotBlank
    @Size(min = 3, max = 50)
    private String password;

    @NotBlank
    @Size(min = 3, max = 50)
    private String confirmPassword;

    public String getPassword() {
        return password;
    }

    public UserPasswordChangeDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserPasswordChangeDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
