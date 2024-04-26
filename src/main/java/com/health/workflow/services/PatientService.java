package com.health.workflow.services;

import com.health.workflow.dto.PatientDTO;
import com.health.workflow.models.PatientModel;

import java.util.List;

public interface PatientService extends CrudService<PatientDTO, PatientModel> {

    List<PatientDTO> findByCustomer(String customerName);
}
