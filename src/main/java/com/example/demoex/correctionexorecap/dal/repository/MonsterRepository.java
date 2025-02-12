package com.example.demoex.correctionexorecap.dal.repository;

import com.example.demoex.correctionexorecap.domain.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, Long> {
    @Query("SELECT m FROM Monster m WHERE m.room.id = :roomId")
    List<Monster> findByRoomId(Long roomId);

    boolean existsByNameAndRoomId(String name, Long roomId);
}
