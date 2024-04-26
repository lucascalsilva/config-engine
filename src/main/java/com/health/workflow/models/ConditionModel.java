package com.health.workflow.models;

import com.health.workflow.enums.Severities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConditionModel extends BaseModel {

    private String conditionId;
    private String conditionName;
    private String description;
    private Severities severity;

}
