package com.mark.tfl.Controllers;

import com.mark.tfl.Models.LineStatus;
import com.mark.tfl.Models.MongoTFLObject;
import com.mark.tfl.Services.TFLStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/mongo")
public class MongoController {

    private static List<LineStatus> lineStatuses;

    private static void getIt(){
//        Not good as creates a whole new TFLStatusService object :(
        TFLStatusService tflStatusService = new TFLStatusService();
        tflStatusService.scheduleAPICall();
        lineStatuses = tflStatusService.getLineStatuses();
    }

    @Autowired
    private TFLRepository tflRepository;

    @RequestMapping("/savenew")
    public void mongo() {
        getIt();
        LocalTime time = LocalTime.now();
        String ttime = time.format(DateTimeFormatter.ISO_LOCAL_TIME);
        MongoTFLObject mongoTFLObject = new MongoTFLObject(ttime, lineStatuses);
        tflRepository.save(mongoTFLObject);
    }
}