package com.health.workflow.repositories;

import com.health.workflow.models.ConfigurationModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigurationRepository extends MongoRepository<ConfigurationModel, String> {

    ConfigurationModel findByCustomer(String customer);

}
