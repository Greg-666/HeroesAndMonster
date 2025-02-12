package com.example.demoex.correctionexorecap.api.dto.dto;

import com.example.demoex.correctionexorecap.domain.Monster;
import com.example.demoex.correctionexorecap.domain.enums.Regime;

import java.util.Set;
import java.util.stream.Collectors;

public record MonsterDTO(
        Long id,
        String name,
        Regime regime,
        Long roomId,
        Set<TypeDTO> types
) {
    public static MonsterDTO fromEntity(Monster monster) {
        return new MonsterDTO(
                monster.getId(),
                monster.getName(),
                monster.getRegime(),
                monster.getRoom().getId(),
                monster.getTypes().stream()
                        .map(TypeDTO::fromEntity)
                        .collect(Collectors.toSet())
        );
    }
}
