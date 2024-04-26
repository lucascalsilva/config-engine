package com.health.workflow.repositories;

import com.health.workflow.models.PatientModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PatientRepository extends MongoRepository<PatientModel, String> {

    List<PatientModel> findByCustomer(String customer);
}
