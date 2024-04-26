package com.health.workflow.repositories;

import com.health.workflow.models.ConditionModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConditionRepository extends MongoRepository<ConditionModel, String> {
}
