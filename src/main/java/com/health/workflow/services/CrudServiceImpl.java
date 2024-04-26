package com.health.workflow.services;

import com.health.workflow.dto.BaseDTO;
import com.health.workflow.mappers.BaseMapper;
import com.health.workflow.models.BaseModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public abstract class CrudServiceImpl<T extends BaseDTO, Z extends BaseModel> implements CrudService<T, Z> {

    protected final BaseMapper<T, Z> mapper;
    protected final MongoRepository<Z, String> repository;

    protected CrudServiceImpl(BaseMapper<T, Z> mapper, MongoRepository<Z, String> repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public T create(T dto){
        Z entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.insert(entity));
    }

    public List<T> createAll(List<T> dto){
        return dto.stream().map(this::create).toList();
    }

    public void delete(String id){
        repository.deleteById(id);
    }

    public List<T> findAll(){
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }
}
