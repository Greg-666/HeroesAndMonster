package com.example.demoex.correctionexorecap.bll.exception.roomFull;

import com.example.demoex.correctionexorecap.bll.exception.DungeonException;

public class RoomFullException extends DungeonException {
    public RoomFullException(String message) {
        super(message, 409);
    }
}
