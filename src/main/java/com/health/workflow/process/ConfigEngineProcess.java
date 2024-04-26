package com.health.workflow.process;

import com.health.workflow.dto.*;
import com.health.workflow.enums.TimesOfDay;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.softcannery.camunda.FormioContext;
import org.softcannery.formio.model.SubmissionValue;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

@UtilityClass
public class ConfigEngineProcess {
    private static final String CUSTOMER_VAR = "customer";
    private static final String CONFIGURATION_VAR = "configuration";
    private static final String PATIENTS_VAR = "patients";
    private static final String PATIENT_VAR = "patient";
    private static final String NEXT_EXECUTION_DATE_VAR = "nextExecutionDate";
    private static final String EVALUATE_STEPS_RESULT_VAR = "evaluateStepsResult";
    private static final String TIME_DAY_VAR = "timeOfDay";
    private static final String TYPE_CONTENT_VAR = "typeContent";
    private static final String OFFSET_DAYS_VAR = "offsetDays";
    private static final String CONFIGS_SUBMITTED_VAR = "configsSubmitted";
    private static final String PUBLISHED_CONFIG_ID_VAR = "publishedConfigId";
    private static final String REC_PROCESS_STARTED_MESSAGE_NAME = "RecProcessStartedMessage";
    private static final String ITERATION_NUMBER_VAR = "iterationNumber";

    public RecProcessResponseDTO createRecProcess(RecProcessDTO recProcess, RuntimeService runtimeService, ObjectMapper objectMapper){
        Map<String, Object> variables = objectMapper
                .convertValue(recProcess, new TypeReference<Map<String, Object>>() {});

        ProcessInstance processInstance = runtimeService.createMessageCorrelation(REC_PROCESS_STARTED_MESSAGE_NAME)
                .setVariables(variables)
                .correlateStartMessage();

        return RecProcessResponseDTO.builder().recProcessInstanceId(processInstance.getId()).build();
    }

    public String getCustomer(DelegateExecution delegateExecution){
        return (String) delegateExecution.getVariable(CUSTOMER_VAR);
    }

    public List<PatientDTO> getPatients(DelegateExecution delegateExecution){
        return (List<PatientDTO>) delegateExecution.getVariable(PATIENTS_VAR);
    }

    public PatientDTO getPatient(DelegateExecution delegateExecution){
        return (PatientDTO) delegateExecution.getVariable(PATIENT_VAR);
    }

    public TimesOfDay getRecTimeOfDay(DelegateExecution delegateExecution){
        Map<String, Object> evaluateStepsResult = getEvaluateStepsResult(delegateExecution);

        return evaluateStepsResult != null ?
                TimesOfDay.valueOf(String.valueOf(evaluateStepsResult.getOrDefault(TIME_DAY_VAR, null))) : null;
    }

    public Long getOffsetDays(DelegateExecution delegateExecution){
        Map<String, Object> evaluateStepsResult = getEvaluateStepsResult(delegateExecution);

        return evaluateStepsResult != null ?
                Long.valueOf(String.valueOf(evaluateStepsResult.getOrDefault(OFFSET_DAYS_VAR, null))) : null;

    }

    public String getRecTypeContent(DelegateExecution delegateExecution){
        Map<String, Object> evaluateStepsResult = getEvaluateStepsResult(delegateExecution);

        return evaluateStepsResult != null ?
                String.valueOf(evaluateStepsResult.getOrDefault(TYPE_CONTENT_VAR, null)) : null;

    }

    public ConfigurationDTO getConfiguration(DelegateExecution delegateExecution){
        return (ConfigurationDTO) delegateExecution.getVariable(CONFIGURATION_VAR);
    }

    public Long getIterationNumber(DelegateExecution delegateExecution){
        return Long.valueOf(String.valueOf(delegateExecution.getVariable(ITERATION_NUMBER_VAR)));
    }

    public void incrementIterationNumber(DelegateExecution delegateExecution){
        Long iterationNumber = getIterationNumber(delegateExecution) + 1;
        delegateExecution.setVariable(ITERATION_NUMBER_VAR, iterationNumber);
    }

    public void setPatients(DelegateExecution delegateExecution, List<PatientDTO> patients){
        delegateExecution.setVariable(PATIENTS_VAR, buildVariable(patients));
    }

    public void setConfiguration(DelegateExecution delegateExecution, ConfigurationDTO configuration){
        delegateExecution.setVariable(CONFIGURATION_VAR, buildVariable(configuration));
    }

    public void setNextExecutionDate(DelegateExecution delegateExecution){
        LocalDateTime nextExecutionDate = LocalDateTime.now().plusMinutes(getOffsetDays(delegateExecution));
        Date nextExecutionDate_ = Date.from(nextExecutionDate.atZone(ZoneId.systemDefault()).toInstant());
        delegateExecution.setVariable(NEXT_EXECUTION_DATE_VAR, buildVariable(nextExecutionDate_));
    }

    public void setPublishedConfigId(DelegateExecution delegateExecution, String publishedConfigId){
        delegateExecution.setVariable(PUBLISHED_CONFIG_ID_VAR, publishedConfigId);
    }

    public PublishConfigProcessDTO getPublishConfig(FormioContext formioContext, ObjectMapper objectMapper){
        Map<String, Object> submissions = formioContext.getSubmissions();
        SubmissionValue submissionValue = (SubmissionValue) submissions.get(CONFIGS_SUBMITTED_VAR);
        return objectMapper.convertValue(submissionValue.getData(),PublishConfigProcessDTO.class);
    }

    private <T> ObjectValue buildVariable(T variable){
        return Variables
                .objectValue(variable)
                .serializationDataFormat("application/json").create();
    }

    private Map<String, Object> getEvaluateStepsResult(DelegateExecution delegateExecution){
        return (Map<String, Object>) delegateExecution.getVariable(EVALUATE_STEPS_RESULT_VAR);
    }
}
