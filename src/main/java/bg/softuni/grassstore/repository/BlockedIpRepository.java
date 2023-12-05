package bg.softuni.grassstore.repository;

import bg.softuni.grassstore.model.entity.BlockedIPsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockedIpRepository extends JpaRepository<BlockedIPsEntity, Long> {
}
