package com.example.demoex.correctionexorecap.api.dto.form;

import com.example.demoex.correctionexorecap.domain.Equipment;
import com.example.demoex.correctionexorecap.domain.Hero;
import jakarta.validation.constraints.*;

public record EquipmentCreateForm(
        @NotBlank(message = "La description est obligatoire")
        String description,

        @NotNull(message = "L'ID du héros est obligatoire")
        Long heroId
) {
    public Equipment toEntity(Hero hero) {
        Equipment equipment = new Equipment();
        equipment.setDescription(description);
        equipment.setHero(hero);
        return equipment;
    }
}
