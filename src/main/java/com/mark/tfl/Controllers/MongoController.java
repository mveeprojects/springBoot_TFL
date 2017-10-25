package com.mark.tfl.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Look at Otter

@RestController
@RequestMapping("/mongo")
public class MongoController {

//    private static List<LineStatus> lineStatuses;

//    private static void getIt(){
////        Not good as creates a whole new TFLStatusService object :(
//        TFLStatusService tflStatusService = new TFLStatusService();
//        tflStatusService.scheduleAPICall();
//        lineStatuses = tflStatusService.getLineStatuses();
//    }

//    @Autowired
//    private TFLRepository tflRepository;
//
//    @RequestMapping("/savenew")
//    public void mongo() {
//        getIt();
//        LocalTime time = LocalTime.now();
//        String ttime = time.format(DateTimeFormatter.ISO_LOCAL_TIME);
//        MongoTFLObject mongoTFLObject = new MongoTFLObject(ttime, lineStatuses);
//        tflRepository.save(mongoTFLObject);
//    }
}