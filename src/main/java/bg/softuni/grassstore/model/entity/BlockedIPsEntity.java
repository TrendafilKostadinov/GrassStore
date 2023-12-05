package bg.softuni.grassstore.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table (name = "blocked_ips")
@Entity
public class BlockedIPsEntity extends BaseEntity {

    private String blockedIp;

    public String getBlockedIp() {
        return blockedIp;
    }

    public BlockedIPsEntity setBlockedIp(String blockedIp) {
        this.blockedIp = blockedIp;
        return this;
    }

    @Override
    public String toString() {
        return blockedIp;
    }
}
