package com.mark.tfl;

import com.mark.tfl.Controllers.TFLController;
import com.mark.tfl.Services.SchedulingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import static com.mark.tfl.Services.TimeService.getCurrentTime;

@SpringBootApplication
@EnableScheduling
public class Application {

    private static final Logger log = LoggerFactory.getLogger(SchedulingService.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        String time = getCurrentTime();
        log.info("App start time - " + time);
        TFLController.lastScheduledRuntime(time);
    }
}