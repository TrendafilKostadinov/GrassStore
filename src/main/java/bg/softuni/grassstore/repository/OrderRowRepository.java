package bg.softuni.grassstore.repository;

import bg.softuni.grassstore.model.entity.OrderRowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRowRepository extends JpaRepository<OrderRowEntity, Long> {
}
