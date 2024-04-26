package com.health.workflow.services;

import com.health.workflow.dto.ConfigurationDTO;
import com.health.workflow.mappers.ConfigurationMapper;
import com.health.workflow.models.ConfigurationModel;
import com.health.workflow.repositories.ConfigurationRepository;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationServiceImpl extends CrudServiceImpl<ConfigurationDTO,
        ConfigurationModel> implements ConfigurationService {

    private final ConfigurationRepository configRepository;
    private final ConfigurationMapper configMapper;

    public ConfigurationServiceImpl(ConfigurationMapper mapper,
                                    ConfigurationRepository repository) {
        super(mapper, repository);
        this.configRepository = repository;
        this.configMapper = mapper;
    }

    @Override
    public ConfigurationDTO findByCustomer(String customerName) {
        return configMapper.toDTO(configRepository.findByCustomer(customerName));
    }
}
