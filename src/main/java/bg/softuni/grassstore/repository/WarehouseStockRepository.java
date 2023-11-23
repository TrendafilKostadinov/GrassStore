package bg.softuni.grassstore.repository;

import bg.softuni.grassstore.model.entity.ProductEntity;
import bg.softuni.grassstore.model.entity.WarehouseStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarehouseStockRepository extends JpaRepository<WarehouseStock, Long> {

    Optional<WarehouseStock> findByProduct(ProductEntity product);
}
