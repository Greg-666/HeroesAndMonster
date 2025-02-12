package com.example.demoex.correctionexorecap.domain;

import com.example.demoex.correctionexorecap.domain.base.BaseEntity;
import com.example.demoex.correctionexorecap.domain.enums.Regime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Monster extends BaseEntity<Long> {
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Regime regime;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToMany
    @JoinTable(
            name = "monster_type",
            joinColumns = @JoinColumn(name = "monster_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    private Set<Type> types = new HashSet<>();
}
