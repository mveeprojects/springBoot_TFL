package com.mark.tfl.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class MongoTFLObject {

    @Id
    private String id;
    private String time;
    private List<TFLLineStatus> statusList;

    public MongoTFLObject(String time, List<TFLLineStatus> statusList) {
        this.time = time;
        this.statusList = statusList;
    }
}