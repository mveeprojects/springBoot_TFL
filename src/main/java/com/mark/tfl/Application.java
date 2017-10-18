package com.mark.tfl;

import com.mark.tfl.Controllers.TFLController;
import com.mark.tfl.Services.SchedulingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class Application {

    private static final Logger log = LoggerFactory.getLogger(SchedulingService.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        String time = dateFormat.format(new Date());
        log.info("App start time - " + time);
        TFLController.lastScheduledRuntime(time);
    }
}