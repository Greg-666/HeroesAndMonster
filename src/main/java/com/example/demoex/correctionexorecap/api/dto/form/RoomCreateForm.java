package com.example.demoex.correctionexorecap.api.dto.form;

import com.example.demoex.correctionexorecap.domain.Room;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RoomCreateForm(
        @NotNull(message = "L'occupation maximale est obligatoire")
        @Min(value = 2, message = "L'occupation doit être d'au moins 2")
        Integer occupancy,

        @NotBlank(message = "La description est obligatoire")
        String description
) {
    public Room toEntity() {
        Room room = new Room();
        room.setOccupancy(occupancy);
        room.setDescription(description);
        return room;
    }
}
