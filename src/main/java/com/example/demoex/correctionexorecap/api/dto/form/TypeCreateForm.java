package com.example.demoex.correctionexorecap.api.dto.form;

import com.example.demoex.correctionexorecap.domain.Type;
import jakarta.validation.constraints.NotBlank;

public record TypeCreateForm(
        @NotBlank(message = "La description est obligatoire")
        String description
) {
        public Type toEntity() {
                Type type = new Type();
                type.setDescription(description);
                return type;
        }
}
