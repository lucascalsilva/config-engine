package com.health.workflow.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import static com.health.workflow.process.ConfigEngineProcess.*;

@Component("prePublishDelegate")
public class PrePublishDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        incrementIterationNumber(delegateExecution);
        setNextExecutionDate(delegateExecution);
    }
}
