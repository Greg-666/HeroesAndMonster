package com.example.demoex.correctionexorecap.dal.repository;

import com.example.demoex.correctionexorecap.domain.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {
    @Query("SELECT h FROM Hero h WHERE h.room.id = :roomId")
    List<Hero> findByRoomId(Long roomId);

    boolean existsByNameAndRoomId(String name, Long roomId);
}
