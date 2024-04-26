package com.health.workflow.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfigurationModel extends BaseModel {

    private String customer;
    private List<ConfigInstanceModel> configInstances;
}
