package com.health.workflow.services;

import com.health.workflow.dto.ConfigurationDTO;
import com.health.workflow.dto.PublishConfigProcessDTO;

public interface PublishConfigService {

    ConfigurationDTO publishConfigDTO(PublishConfigProcessDTO publishConfigProcess);
}
