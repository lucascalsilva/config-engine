package com.health.workflow.bootstrap;

import com.health.workflow.dto.ConfigurationDTO;
import com.health.workflow.dto.PatientDTO;
import com.health.workflow.services.ConfigurationService;
import com.health.workflow.services.PatientService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class LoadDataBootstrap implements CommandLineRunner {

    private final ConfigurationService configurationService;
    private final PatientService patientService;
    private final ObjectMapper objectMapper;



    @Override
    @SneakyThrows
    public void run(String... args) throws Exception {
        loadData(configurationService.findAll().isEmpty(), "data/configurations.json",
                new TypeReference<List<ConfigurationDTO>>() {
                },
                configurationService::createAll);

        loadData(patientService.findAll().isEmpty(), "data/patients.json",
                new TypeReference<List<PatientDTO>>() {
                },
                patientService::createAll);
    }

    @SneakyThrows
    public <T> void loadData(Boolean load, String file, TypeReference<T> typeReference, Function<T, T> fn) {
        if (load) {
            InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
            T t = objectMapper.readValue(resourceAsStream, typeReference);
            fn.apply(t);
        }
    }
}
