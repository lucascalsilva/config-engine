package com.health.workflow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfigurationDTO extends BaseDTO {

    private String id = UUID.randomUUID().toString();
    private String customer;
    private List<ConfigInstanceDTO> configInstances;
}
