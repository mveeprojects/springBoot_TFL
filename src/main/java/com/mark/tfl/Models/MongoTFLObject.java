package com.mark.tfl.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "tflresponse_collection")
public class MongoTFLObject {

    @Id
    private String id;
    private String time;
    private List<LineStatus> statusList;

    public MongoTFLObject(String time, List<LineStatus> statusList) {
        this.time = time;
        this.statusList = statusList;
    }
}