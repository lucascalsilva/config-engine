package com.health.workflow.mappers;

import com.health.workflow.dto.PatientDTO;
import com.health.workflow.models.PatientModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper extends BaseMapper<PatientDTO, PatientModel> {
    
}
