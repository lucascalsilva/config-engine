package com.health.workflow.delegates;

import com.health.workflow.dto.*;
import com.health.workflow.services.PublishConfigService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.softcannery.camunda.FormioContext;
import org.springframework.stereotype.Component;

import static com.health.workflow.process.ConfigEngineProcess.*;

@Component("publishConfigs")
@RequiredArgsConstructor
public class PublishConfigsDelegate implements JavaDelegate {

    private final FormioContext formioContext;
    private final ObjectMapper objectMapper;
    private final PublishConfigService publishConfigService;

    @Override
    @SneakyThrows
    public void execute(DelegateExecution delegateExecution) throws Exception {
        PublishConfigProcessDTO publishConfigProcess = getPublishConfig(formioContext, objectMapper);
        ConfigurationDTO configuration = publishConfigService.publishConfigDTO(publishConfigProcess);
        setPublishedConfigId(delegateExecution, configuration.getId());
    }
}
