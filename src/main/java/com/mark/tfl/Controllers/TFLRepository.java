package com.mark.tfl.Controllers;

import com.mark.tfl.Models.MongoTFLObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TFLRepository extends MongoRepository<MongoTFLObject, String> {
}
