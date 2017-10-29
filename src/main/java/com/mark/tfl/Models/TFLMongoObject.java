package com.mark.tfl.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "TFL_Line_Statuses")
public class TFLMongoObject {

    @Id
    private String id;
    private String time;
    private List<TFLLineStatus> statusList;

    public TFLMongoObject(String time, List<TFLLineStatus> statusList) {
        this.time = time;
        this.statusList = statusList;
    }
}