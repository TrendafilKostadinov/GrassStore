package bg.softuni.grassstore.init;

import bg.softuni.grassstore.model.entity.CurrencyEntity;
import bg.softuni.grassstore.model.entity.UserEntity;
import bg.softuni.grassstore.model.entity.UserRoleEntity;
import bg.softuni.grassstore.model.enums.CurrencyNames;
import bg.softuni.grassstore.model.enums.RoleNames;
import bg.softuni.grassstore.repository.CurrencyRepository;
import bg.softuni.grassstore.repository.RolesRepository;
import bg.softuni.grassstore.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    private final RolesRepository rolesRepository;

    private final CurrencyRepository currencyRepository;

    private final UserRepository userRepository;

    private final static String ADMIN_PASS = "35e391ed69df5e69935da0029aa972f4402f113b5671e7af33f780b706b7849f0f0bd022b113bc274660777ef559e1d0";

    public DBInit(RolesRepository rolesRepository,
                  CurrencyRepository currencyRepository,
                  UserRepository userRepository) {
        this.rolesRepository = rolesRepository;
        this.currencyRepository = currencyRepository;
        this.userRepository = userRepository;
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

        if (currencyRepository.count()==0){
            List<CurrencyEntity> currencyList = new ArrayList<>();
            Arrays.stream(CurrencyNames.values()).forEach(currencyName -> {
                CurrencyEntity currencyEntity = new CurrencyEntity();
                currencyEntity.setName(currencyName);
                currencyList.add(currencyEntity);
            });
            currencyRepository.saveAll(currencyList);
        }

        if (userRepository.count()==0){
            UserEntity admin = new UserEntity();
            List<UserRoleEntity> roles = new ArrayList<>();
            roles.add(rolesRepository.findByName(RoleNames.ADMIN));
            admin.setEmail("admin@admin.com")
                    .setPassword(ADMIN_PASS)
                    .setRoles(roles)
                    .setFullName("Admin");

            userRepository.save(admin);
        }
    }
}
