package com.example.demoex.correctionexorecap.dal.repository;

import com.example.demoex.correctionexorecap.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    boolean existsByDescription(String description);
}
