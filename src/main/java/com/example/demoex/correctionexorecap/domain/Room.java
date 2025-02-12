package com.example.demoex.correctionexorecap.domain;

import com.example.demoex.correctionexorecap.domain.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Room extends BaseEntity<Long> {
    @Column(nullable = false)
    private Integer occupancy;

    @Column(nullable = false)
    private String description;
}
