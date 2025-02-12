package com.example.demoex.correctionexorecap.bll.service;

import com.example.demoex.correctionexorecap.domain.Equipment;

import java.util.List;

public interface EquipmentService {
    Long create(Equipment equipment);
    Equipment findById(Long id);
    List<Equipment> findAll();
    List<Equipment> findByHeroId(Long heroId);
    void update(Long id, Equipment equipment);
    void delete(Long id);
}
