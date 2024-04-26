package com.health.workflow.models;

import com.health.workflow.enums.TimesOfDay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientRecModel extends BaseModel {

    private String typeContent;
    private TimesOfDay timeOfDay;
    private PatientModel patient;
}
