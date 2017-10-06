package com.mark.tfl.Services;

import com.mark.tfl.Controllers.Dashboard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SchedulingService {

    private static final Logger log = LoggerFactory.getLogger(SchedulingService.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        String time = dateFormat.format(new Date());
        log.info("SchedulingService - " + time);
        Dashboard.lastScheduledRuntime(time);
    }
}
