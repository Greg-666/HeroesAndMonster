package com.example.demoex.correctionexorecap.api.dto.form;

import com.example.demoex.correctionexorecap.domain.Hero;
import com.example.demoex.correctionexorecap.domain.Room;
import jakarta.validation.constraints.NotBlank;

public record HeroCreateForm(
        @NotBlank(message = "Le nom est obligatoire")
        String name,

        @NotBlank(message = "La profession est obligatoire")
        String profession,

        Long roomId
) {
    public Hero toEntity(Room room) {
        Hero hero = new Hero();
        hero.setName(name);
        hero.setProfession(profession);
        hero.setRoom(room);
        return hero;
    }
}
