package com.health.workflow.repositories;

import com.health.workflow.models.PatientRecModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRecRepository extends MongoRepository<PatientRecModel, String> {

}
