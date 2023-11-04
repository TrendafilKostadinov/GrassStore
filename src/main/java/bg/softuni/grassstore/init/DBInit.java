package bg.softuni.grassstore.init;

import bg.softuni.grassstore.model.entity.UserRoleEntity;
import bg.softuni.grassstore.model.enums.RoleNames;
import bg.softuni.grassstore.repository.RolesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    private final RolesRepository rolesRepository;

    public DBInit(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (rolesRepository.count()==0){
            List<UserRoleEntity> userRoles = new ArrayList<>();
            Arrays.stream(RoleNames.values()).forEach(roleName -> {
                UserRoleEntity usersRolesEntity = new UserRoleEntity();
                usersRolesEntity.setName(roleName);
                userRoles.add(usersRolesEntity);
            });
            rolesRepository.saveAll(userRoles);
        }
    }
}
