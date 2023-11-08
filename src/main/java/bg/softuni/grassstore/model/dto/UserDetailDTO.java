package bg.softuni.grassstore.model.dto;

public class UserDetailDTO {

    private Long id;

    private String email;

    private String fullName;

    private String allRoles;

    public String getAllRoles() {
        return allRoles;
    }

    public UserDetailDTO setAllRoles(String allRoles) {
        this.allRoles = allRoles;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserDetailDTO setId(Long id) {
        this.id = id;
        return this;
    }

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
