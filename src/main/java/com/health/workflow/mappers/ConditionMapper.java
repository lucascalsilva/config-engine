package com.health.workflow.mappers;

import com.health.workflow.dto.ConditionDTO;
import com.health.workflow.models.ConditionModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConditionMapper extends BaseMapper<ConditionDTO, ConditionModel> {

}
