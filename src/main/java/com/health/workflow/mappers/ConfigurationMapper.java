package com.health.workflow.mappers;

import com.health.workflow.dto.ConfigurationDTO;
import com.health.workflow.models.ConfigurationModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConfigurationMapper extends BaseMapper<ConfigurationDTO,
        ConfigurationModel> {

}
