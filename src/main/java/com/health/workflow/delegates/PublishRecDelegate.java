package com.health.workflow.delegates;

import com.health.workflow.dto.PatientDTO;
import com.health.workflow.dto.PatientRecDTO;
import com.health.workflow.enums.TimesOfDay;
import com.health.workflow.services.PatientRecService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import static com.health.workflow.process.ConfigEngineProcess.*;

@Component("publishRecommendations")
@RequiredArgsConstructor
@Slf4j
public class PublishRecDelegate implements JavaDelegate {

    private final PatientRecService patientRecService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String typeContent = getRecTypeContent(delegateExecution);
        TimesOfDay timeOfDay = getRecTimeOfDay(delegateExecution);

        PatientDTO patient = getPatient(delegateExecution);
        PatientRecDTO patientRecDTO = PatientRecDTO.builder().patient(patient)
                .customer(patient.getCustomer())
                .timeOfDay(timeOfDay)
                .typeContent(typeContent).build();

        patientRecService.create(patientRecDTO);
    }
}
