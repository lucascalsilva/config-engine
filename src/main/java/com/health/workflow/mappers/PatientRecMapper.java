package com.health.workflow.mappers;

import com.health.workflow.dto.PatientRecDTO;
import com.health.workflow.models.PatientRecModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientRecMapper extends BaseMapper<PatientRecDTO, PatientRecModel> {
    
}
