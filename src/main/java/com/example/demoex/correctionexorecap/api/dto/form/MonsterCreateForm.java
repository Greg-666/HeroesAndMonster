package com.example.demoex.correctionexorecap.api.dto.form;

import com.example.demoex.correctionexorecap.domain.Monster;
import com.example.demoex.correctionexorecap.domain.Room;
import com.example.demoex.correctionexorecap.domain.Type;
import com.example.demoex.correctionexorecap.domain.enums.Regime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record MonsterCreateForm(
        @NotBlank(message = "Le nom est obligatoire")
        String name,

        @NotNull(message = "Le régime est obligatoire")
        Regime regime,

        @NotNull(message = "L'ID de la salle est obligatoire")
        Long roomId,

        @NotEmpty(message = "Le monstre doit avoir au moins un type")
        Set<Long> typeIds
) {
    public Monster toEntity(Room room, Set<Type> types) {
        Monster monster = new Monster();
        monster.setName(name);
        monster.setRegime(regime);
        monster.setRoom(room);
        monster.setTypes(types);
        return monster;
    }
}
