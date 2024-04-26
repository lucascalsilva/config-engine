package com.health.workflow.services;

import com.health.workflow.dto.ConfigurationDTO;
import com.health.workflow.models.ConfigurationModel;

public interface ConfigurationService extends CrudService<ConfigurationDTO, ConfigurationModel> {

    ConfigurationDTO findByCustomer(String customerName);
}
