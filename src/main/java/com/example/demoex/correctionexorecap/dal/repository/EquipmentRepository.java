package com.example.demoex.correctionexorecap.dal.repository;

import com.example.demoex.correctionexorecap.domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    @Query("SELECT e FROM Equipment e WHERE e.hero.id = :heroId")
    List<Equipment> findByHeroId(Long heroId);
}
