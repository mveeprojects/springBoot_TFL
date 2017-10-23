package com.mark.tfl.Utils;

import com.mark.tfl.Models.LineStatus;
import com.mark.tfl.Models.MongoTFLObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class TFLMongoActions {

    @Autowired
    private static TFLRepository repository;

    public static void saveToMongo(List<LineStatus> allLineStatuses) {
        String localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("h:mm a"));
        MongoTFLObject mongoTFLObject = new MongoTFLObject(localDateTime, allLineStatuses);
        repository.save(mongoTFLObject);
    }
}