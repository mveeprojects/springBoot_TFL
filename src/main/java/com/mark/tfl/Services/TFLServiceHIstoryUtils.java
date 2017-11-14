package com.mark.tfl.Services;

import com.mark.tfl.Models.TFLLineHistoryObject;
import com.mark.tfl.Models.TFLMongoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TFLServiceHIstoryUtils {

    @Autowired
    TFLMongoRepo tflMongoRepo;

    private String lineName;
    private List<TFLLineHistoryObject> lineHistories;

    List<TFLLineHistoryObject> getLineStatusHistoryFromMongo(String lineName) {
        this.lineName = lineName;
        lineHistories = new ArrayList<>();
        tflMongoRepo.findAll()
                .forEach(tflMongoObject -> tflMongoObject.getStatusList()
                        .forEach(tflLineStatus -> {
                            if (tflLineStatus.getLineName().contains(lineName)) {
                                lineHistories.add(new TFLLineHistoryObject(tflMongoObject.getTime(), tflLineStatus.getLineName(), tflLineStatus.getLineStatus()));
                            }
                        }));
        return lineHistories;
    }

    long getGoodHistoryCount(String lineName) {
        checkLineStatusesStoredAlready(lineName);
        return lineHistories.stream()
                .filter(status -> "Good Service".equals(status.getLineStatus()))
                .count();
    }

    long getNotGoodHistoryCount(String lineName) {
        checkLineStatusesStoredAlready(lineName);
        return lineHistories.stream()
                .filter(status -> !"Good Service".equals(status.getLineStatus()))
                .count();
    }

    private void checkLineStatusesStoredAlready(String line){
        if(!line.equals(this.lineName)){
            getLineStatusHistoryFromMongo(line);
        }
    }
}
