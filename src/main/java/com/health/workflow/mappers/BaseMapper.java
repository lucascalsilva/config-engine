package com.health.workflow.mappers;

import com.health.workflow.dto.BaseDTO;
import com.health.workflow.models.BaseModel;

public interface BaseMapper<T extends BaseDTO, Z extends BaseModel> {

    T toDTO(Z entity);
    Z toEntity(T dto);
}
