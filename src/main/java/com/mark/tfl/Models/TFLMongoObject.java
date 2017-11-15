package com.mark.tfl.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

//@Document(collection = "TFL_Line_Statuses")
@Document(collection = "TFLTestData")
public class TFLMongoObject {

    @Id
    private String id;
    private String time;
    private List<TFLLineStatusObject> statusList;

    public TFLMongoObject(String time, List<TFLLineStatusObject> statusList) {
        this.time = time;
        this.statusList = statusList;
    }

    public List<TFLLineStatusObject> getStatusList() {
        return statusList;
    }

    public String getTime() {
        return time;
    }
}