package com.example.demoex.correctionexorecap.bll.exception.ressourceAlreadyExists;

import com.example.demoex.correctionexorecap.bll.exception.DungeonException;

public class RessourceAlreadyExistsException extends DungeonException {
    public RessourceAlreadyExistsException(String message) {
        super(message, 409);
    }

}
