package com.health.workflow.services;

import com.health.workflow.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.model.dmn.Dmn;
import org.camunda.bpm.model.dmn.DmnModelInstance;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Base64;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@RequiredArgsConstructor
public class PublishConfigServiceImpl implements PublishConfigService {

    private final RepositoryService repositoryService;
    private final ConfigurationService configurationService;
    @Override
    public ConfigurationDTO publishConfigDTO(PublishConfigProcessDTO publishConfigProcess) {
        ConfigurationDTO.ConfigurationDTOBuilder configBuilder = ConfigurationDTO.builder();

        List<ConfigInstanceDTO> configInstances = publishConfigProcess.getConfigurationList()
                .stream().map(this::buildConfigInstance).toList();

        ConfigurationDTO configuration = configBuilder.configInstances(configInstances).customer(publishConfigProcess.getCustomerName()).build();
        return configurationService.create(configuration);
    }

    @SneakyThrows
    private ConfigInstanceDTO buildConfigInstance(PublishableConfigDTO publishableConfig){
        ConfigInstanceDTO.ConfigInstanceDTOBuilder configInstanceBuilder = ConfigInstanceDTO.builder()
                .instanceName(publishableConfig.getInstanceName());
        List<String> stepDecisions = publishableConfig.getStepDecisions().stream()
                .map(this::deployFile).toList();
        configInstanceBuilder.stepDecisions(stepDecisions);
        return configInstanceBuilder.build();
    }

    @SneakyThrows
    private String deployFile(PublishableStepDecisionDTO publishableStepDecision){
        FileDTO fileDTO = publishableStepDecision.getDecisionTableFile().get(0);
        String dmnTableContent = new String(Base64.getDecoder().decode(fileDTO.getBase64Content()));
        DmnModelInstance modelInstance = null;
        try(InputStream dmnInputStream = IOUtils.toInputStream(dmnTableContent, UTF_8)) {
            modelInstance = Dmn.readModelFromStream(dmnInputStream);
            repositoryService.createDeployment()
                    .addModelInstance(fileDTO.getOriginalName(), modelInstance).deployWithResult();
        }

        return modelInstance.getDefinitions().getId();
    }
}
