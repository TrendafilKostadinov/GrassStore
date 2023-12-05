package bg.softuni.grassstore.service;

import bg.softuni.grassstore.repository.BlockedIpRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockedIpService {

    private final BlockedIpRepository blockedIpRepository;

    public BlockedIpService(BlockedIpRepository blockedIpRepository) {
        this.blockedIpRepository = blockedIpRepository;
    }

    public List<String> getBlockedIps(){
        return blockedIpRepository
                .findAll()
                .stream()
                .map(Object::toString).toList();
    }
}
