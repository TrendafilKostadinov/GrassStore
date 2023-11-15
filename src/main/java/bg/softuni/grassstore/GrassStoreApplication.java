package bg.softuni.grassstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class GrassStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrassStoreApplication.class, args);
    }

}
