package com.example.demoex.correctionexorecap.bll.service;

import com.example.demoex.correctionexorecap.domain.Monster;

import java.util.List;

public interface MonsterService {
    Long create(Monster monster);
    Monster findById(Long id);
    List<Monster> findAll();
    List<Monster> findByRoomId(Long roomId);
    void update(Long id, Monster monster);
    void delete(Long id);
}
