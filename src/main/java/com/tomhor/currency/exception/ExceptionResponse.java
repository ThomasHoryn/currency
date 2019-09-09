package com.tomhor.currency.exception;

import lombok.Getter;

import java.time.LocalDate;


@Getter
public class ExceptionResponse {
    private LocalDate timestamp;
    private String message;

    public ExceptionResponse(LocalDate timestamp, String message) {
        super();
        this.timestamp = timestamp;
        this.message = message;
    }
}
