package com.health.workflow.services;

import com.health.workflow.dto.ConditionDTO;
import com.health.workflow.mappers.ConditionMapper;
import com.health.workflow.models.ConditionModel;
import com.health.workflow.repositories.ConditionRepository;
import org.springframework.stereotype.Service;

@Service
public class ConditionServiceImpl extends CrudServiceImpl<ConditionDTO, ConditionModel> implements ConditionService{

    public ConditionServiceImpl(ConditionMapper mapper, ConditionRepository repository) {
        super(mapper, repository);
    }
}
