package bg.softuni.grassstore.repository;

import bg.softuni.grassstore.model.entity.WarehouseStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseStockRepository extends JpaRepository<WarehouseStock, Long> {
}
