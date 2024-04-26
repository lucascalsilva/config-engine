package com.health.workflow.models;

import com.health.workflow.enums.Frequencies;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientModel extends BaseModel {

    private String patientId;
    private String name;
    private String email;
    private String customer;
    private Long age;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateOfBirth;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime enrollmentDateTime;
    private Frequencies physicalActivityFreq;
    private Frequencies smokeFrequency;
    private Frequencies alcoholFrequency;
    private List<ConditionModel> existingConditions;

    public Long getAge(){
        if(this.dateOfBirth != null){
            return ChronoUnit.YEARS.between(dateOfBirth, LocalDateTime.now());
        }
        return null;
    }
}
