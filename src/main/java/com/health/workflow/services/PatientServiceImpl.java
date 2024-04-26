package com.health.workflow.services;

import com.health.workflow.dto.PatientDTO;
import com.health.workflow.mappers.PatientMapper;
import com.health.workflow.models.PatientModel;
import com.health.workflow.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl extends CrudServiceImpl<PatientDTO, PatientModel> implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientServiceImpl(PatientMapper mapper, PatientRepository repository) {
        super(mapper, repository);
        this.patientRepository = repository;
        this.patientMapper = mapper;
    }

    @Override
    public List<PatientDTO> findByCustomer(String customer) {
        return patientRepository.findByCustomer(customer).stream()
                .map(patientMapper::toDTO).toList();
    }
}
