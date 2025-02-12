package com.example.demoex.correctionexorecap.api.dto.dto;

import com.example.demoex.correctionexorecap.domain.Type;

public record TypeDTO(
        Long id,
        String description
) {
    public static TypeDTO fromEntity(Type type) {
        return new TypeDTO(
                type.getId(),
                type.getDescription()
        );
    }
}
