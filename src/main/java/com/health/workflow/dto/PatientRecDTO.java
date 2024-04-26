package com.health.workflow.dto;

import com.health.workflow.enums.TimesOfDay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientRecDTO extends BaseDTO {

    private String customer;
    private String typeContent;
    private TimesOfDay timeOfDay;
    private PatientDTO patient;
}
