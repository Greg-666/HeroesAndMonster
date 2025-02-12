package com.example.demoex.correctionexorecap.bll.service;

import com.example.demoex.correctionexorecap.domain.Type;

import java.util.List;
import java.util.Set;

public interface TypeService {
    Long create(Type type);
    Type findById(Long id);
    List<Type> findAll();
    void update(Long id, Type type);
    void delete(Long id);
    Set<Type> findAllByIds(Set<Long> ids);
}
