package com.health.workflow.mappers;

import com.health.workflow.dto.ConfigInstanceDTO;
import com.health.workflow.models.ConfigInstanceModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConfigInstanceMapper extends BaseMapper<ConfigInstanceDTO,
        ConfigInstanceModel> {

}
