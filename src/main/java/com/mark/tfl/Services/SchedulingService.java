package com.mark.tfl.Services;

import com.mark.tfl.Controllers.TFLController;
import com.mark.tfl.Utils.TimeUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulingService {

    private static final Logger log = LoggerFactory.getLogger(SchedulingService.class);

    private TFLController tflController;

    @Autowired
    public SchedulingService(TFLController tflController) {
        this.tflController = tflController;
    }

    @Autowired
    private TimeUtility timeUtility;

    @Scheduled(cron = "0 */30 * * * *")
    public void reportCurrentTime() {
        String time = timeUtility.getCurrentTimeAsString();
        log.info("SchedulingService - " + time);
        tflController.scheduledDataRefresh(time);
    }
}