package com.health.workflow.services;

import com.health.workflow.dto.PatientRecDTO;
import com.health.workflow.mappers.PatientRecMapper;
import com.health.workflow.models.PatientRecModel;
import com.health.workflow.repositories.PatientRecRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientRecServiceImpl extends CrudServiceImpl<PatientRecDTO, PatientRecModel> implements PatientRecService {

    protected PatientRecServiceImpl(PatientRecMapper mapper, PatientRecRepository repository) {
        super(mapper, repository);
    }
}
