package com.mark.tfl.Utils;

import com.mark.tfl.Models.MongoTFLObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// Not being called by a REST API, just in code so might be wrong annotation
@RepositoryRestResource
public interface TFLRepository extends MongoRepository<MongoTFLObject, String> {
}