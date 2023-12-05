package bg.softuni.grassstore.service.scheduler;

import bg.softuni.grassstore.service.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class SystemUsageLogScheduler {

    private final UserService userService;

    public SystemUsageLogScheduler(UserService userService) {
        this.userService = userService;
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    public void logSystemUsage() throws IOException {

        File file = new File("src/main/resources/system/system-usage.txt");

        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

        List<User> principals = userService.getPrincipals();

        String log = LocalDateTime.now() + " - " +
                principals.size() +
                " user/s logged to the system." +
                System.lineSeparator();

        writer.append(log);
        writer.close();

    }


}
