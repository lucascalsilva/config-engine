package com.health.workflow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PublishableConfigDTO {

    private String instanceName;
    private List<PublishableStepDecisionDTO> stepDecisions;
}
