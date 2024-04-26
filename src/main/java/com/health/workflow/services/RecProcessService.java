package com.health.workflow.services;

import com.health.workflow.dto.RecProcessDTO;
import com.health.workflow.dto.RecProcessResponseDTO;

public interface RecProcessService {

    RecProcessResponseDTO createRecProcess(RecProcessDTO recProcess);
}
