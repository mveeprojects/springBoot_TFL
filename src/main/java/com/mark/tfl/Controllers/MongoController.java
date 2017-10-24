package com.mark.tfl.Controllers;

import com.mark.tfl.Models.LineStatus;
import com.mark.tfl.Models.MongoTFLObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mongo")
public class MongoController {

    @Autowired
    private TFLRepository tflRepository;

    @RequestMapping("/savenew")
    public void mongo() {
        LocalTime time = LocalTime.now();
        String ttime = time.format(DateTimeFormatter.ISO_LOCAL_TIME);
        List<LineStatus> testList = new ArrayList<LineStatus>();
        testList.add(new LineStatus("testLine", "testStatus"));
        MongoTFLObject mongoTFLObject = new MongoTFLObject(ttime, testList);
        tflRepository.save(mongoTFLObject);
    }
}