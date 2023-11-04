package bg.softuni.grassstore.model.entity;

import bg.softuni.grassstore.model.enums.RoleNames;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private RoleNames name;

    public RoleNames getName() {
        return name;
    }

    public UserRoleEntity setName(RoleNames name) {
        this.name = name;
        return this;
    }
}
