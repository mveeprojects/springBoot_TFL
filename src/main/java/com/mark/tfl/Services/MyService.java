package com.mark.tfl.Services;

import com.mark.tfl.Models.MongoTFLObject;
import com.mark.tfl.Utils.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    public MyService() {
    }

    @Autowired
    MyRepository repository;

    @Autowired
    MongoOperations mongo;

    public void savethings(MongoTFLObject mongoTFLObject) {
        repository.save(mongoTFLObject);
    }
}
