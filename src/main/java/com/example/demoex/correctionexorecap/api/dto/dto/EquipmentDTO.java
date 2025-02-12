package com.example.demoex.correctionexorecap.api.dto.dto;

import com.example.demoex.correctionexorecap.domain.Equipment;

public record EquipmentDTO(
        Long id,
        String description,
        Long heroId
) {
    public static EquipmentDTO fromEntity(Equipment equipment) {
        return new EquipmentDTO(
                equipment.getId(),
                equipment.getDescription(),
                equipment.getHero().getId()
        );
    }
}
