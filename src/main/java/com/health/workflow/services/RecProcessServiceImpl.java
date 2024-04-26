package com.health.workflow.services;

import com.health.workflow.dto.RecProcessDTO;
import com.health.workflow.dto.RecProcessResponseDTO;
import com.health.workflow.process.ConfigEngineProcess;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecProcessServiceImpl implements RecProcessService {

    private final RuntimeService runtimeService;
    private final ObjectMapper objectMapper;
    @Override
    public RecProcessResponseDTO createRecProcess(RecProcessDTO recProcess) {
        return ConfigEngineProcess.createRecProcess(recProcess, runtimeService, objectMapper);
    }
}
