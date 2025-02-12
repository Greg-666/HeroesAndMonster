package com.example.demoex.correctionexorecap.api.controller.advisor;

import com.example.demoex.correctionexorecap.bll.exception.DungeonException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(DungeonException.class)
    public ResponseEntity<Object> handleDungeonException(DungeonException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(ex.getMessage());
    }
}
