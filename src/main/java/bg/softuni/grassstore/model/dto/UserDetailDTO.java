package bg.softuni.grassstore.model.dto;

public class UserDetailDTO {

    private String email;

    private String fullName;

    public String getEmail() {
        return email;
    }

    public UserDetailDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserDetailDTO setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
}
