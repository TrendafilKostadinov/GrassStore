package bg.softuni.grassstore.repositories;

import bg.softuni.grassstore.model.entity.UsersRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<UsersRolesEntity, Long> {
}
