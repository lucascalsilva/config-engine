package com.health.workflow.delegates;

import com.health.workflow.dto.PatientDTO;
import com.health.workflow.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.health.workflow.process.ConfigEngineProcess.*;

@Component("fetchPatients")
@RequiredArgsConstructor
public class FetchPatientsDelegate implements JavaDelegate {

    private final PatientService patientService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<PatientDTO> patients = patientService
                .findByCustomer(getCustomer(delegateExecution));

        setPatients(delegateExecution, patients);

    }
}
