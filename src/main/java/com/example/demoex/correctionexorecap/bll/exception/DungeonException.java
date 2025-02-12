package com.example.demoex.correctionexorecap.bll.exception;

import lombok.Getter;

@Getter
public class DungeonException extends RuntimeException {
    private final int status;
    private final Object message;

    public DungeonException(String message) {
        super(message);
        this.status = 500;
        this.message = message;
    }

    public DungeonException(String message, int status) {
        super(message);
        this.status = status;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message.toString();
    }

    @Override
    public String toString() {
        StackTraceElement elem = this.getStackTrace()[0];
        return String.format("%s thrown in %s() at %s:%d with message: %s",
                this.getClass().getSimpleName(),
                elem.getMethodName(),
                elem.getFileName(),
                elem.getLineNumber(),
                this.getMessage());
    }
}
