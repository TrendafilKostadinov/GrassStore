package bg.softuni.grassstore.service.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ExchangeRatesRefreshScheduler {

    @Scheduled(cron = "* 0 */6 * * *")
    public void printTime(){
        System.out.println(LocalDateTime.now());
    }

}
