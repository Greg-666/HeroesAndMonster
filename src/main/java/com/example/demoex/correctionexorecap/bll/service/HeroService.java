package com.example.demoex.correctionexorecap.bll.service;

import com.example.demoex.correctionexorecap.domain.Hero;

import java.util.List;

public interface HeroService {
    Long create(Hero hero);
    Hero findById(Long id);
    List<Hero> findAll();
    List<Hero> findByRoomId(Long roomId);
    void update(Long id, Hero hero);
    void delete(Long id);
}
