package com.ciandt.summit.bootcamp2022.domain.service.exception;

public class LengthValidationException extends Throwable {
    public LengthValidationException(String message) {
        super(message);
    }
}
