package com.health.workflow.delegates;

import com.health.workflow.dto.ConfigurationDTO;
import com.health.workflow.services.ConfigurationService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import static com.health.workflow.process.ConfigEngineProcess.*;

@Component("fetchConfigurations")
@RequiredArgsConstructor
public class FetchConfigsDelegate implements JavaDelegate {

    private final ConfigurationService configurationService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        ConfigurationDTO configuration = configurationService
                .findByCustomer(getCustomer(delegateExecution));

        setConfiguration(delegateExecution, configuration);
    }
}
