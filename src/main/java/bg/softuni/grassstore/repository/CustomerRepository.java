package bg.softuni.grassstore.repository;

import bg.softuni.grassstore.model.entity.CustomerEntity;
import bg.softuni.grassstore.model.entity.UserEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByName(String name);

    List<CustomerEntity> findAllByTrader_Id(Long userId);
}
