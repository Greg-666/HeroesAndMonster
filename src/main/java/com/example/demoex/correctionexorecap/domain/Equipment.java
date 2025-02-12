package com.example.demoex.correctionexorecap.domain;

import com.example.demoex.correctionexorecap.domain.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Equipment extends BaseEntity<Long> {
    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "hero_id", nullable = false)
    private Hero hero;
}
