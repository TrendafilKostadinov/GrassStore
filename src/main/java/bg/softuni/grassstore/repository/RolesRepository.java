package bg.softuni.grassstore.repository;

import bg.softuni.grassstore.model.entity.UserRoleEntity;
import bg.softuni.grassstore.model.enums.RoleNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<UserRoleEntity, Long> {
    UserRoleEntity findByName(RoleNames roleNames);
}
