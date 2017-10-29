package com.mark.tfl.Models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TFLMongoRepo extends MongoRepository<TFLMongoObject, String> {
}