package com.example.demoex.correctionexorecap.api.dto.dto;

import com.example.demoex.correctionexorecap.domain.Room;

public record RoomDTO(
        Long id,
        Integer occupancy,
        String description
) {
    public static RoomDTO fromEntity(Room room) {
        return new RoomDTO(
                room.getId(),
                room.getOccupancy(),
                room.getDescription()
        );
    }
}
