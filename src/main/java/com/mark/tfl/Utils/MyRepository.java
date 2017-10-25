package com.mark.tfl.Utils;

import com.mark.tfl.Models.MongoTFLObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyRepository extends MongoRepository<MongoTFLObject, String> {

}