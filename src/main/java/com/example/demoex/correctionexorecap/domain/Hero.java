package com.example.demoex.correctionexorecap.domain;

import com.example.demoex.correctionexorecap.domain.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//premiere entity

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Hero extends BaseEntity<Long> {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String profession;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
