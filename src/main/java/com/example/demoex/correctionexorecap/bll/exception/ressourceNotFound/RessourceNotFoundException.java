package com.example.demoex.correctionexorecap.bll.exception.ressourceNotFound;

import com.example.demoex.correctionexorecap.bll.exception.DungeonException;

public class RessourceNotFoundException extends DungeonException {
    public RessourceNotFoundException(String message) {
        super(message, 404);
    }
}
