package com.mark.tfl.Services;

import com.mark.tfl.Controllers.TFLController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.mark.tfl.Services.TimeService.getCurrentTime;

@Component
public class SchedulingService {

    private static final Logger log = LoggerFactory.getLogger(SchedulingService.class);

    @Scheduled(cron="0 */30 * * * *")
    public void reportCurrentTime() {
        String time = getCurrentTime();
        log.info("SchedulingService - " + time);
        TFLController.lastScheduledRuntime(time);
    }
}