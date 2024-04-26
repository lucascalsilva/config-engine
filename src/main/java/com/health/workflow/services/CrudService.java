package com.health.workflow.services;

import com.health.workflow.dto.BaseDTO;
import com.health.workflow.models.BaseModel;

import java.util.List;

public interface CrudService<T extends BaseDTO, Z extends BaseModel> {

    T create(T dto);
    List<T> createAll(List<T> dto);
    void delete(String id);
    List<T> findAll();
}
