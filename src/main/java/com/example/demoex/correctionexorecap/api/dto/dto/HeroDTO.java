package com.example.demoex.correctionexorecap.api.dto.dto;

import com.example.demoex.correctionexorecap.domain.Hero;

public record HeroDTO(
        Long id,
        String name,
        String profession,
        Long roomId
) {
    public static HeroDTO fromEntity(Hero hero) {
        return new HeroDTO(
                hero.getId(),
                hero.getName(),
                hero.getProfession(),
                hero.getRoom() != null ? hero.getRoom().getId() : null
        );
    }
}
