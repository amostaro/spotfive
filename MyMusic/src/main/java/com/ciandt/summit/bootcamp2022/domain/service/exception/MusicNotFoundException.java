package com.ciandt.summit.bootcamp2022.domain.service.exception;

public class MusicNotFoundException extends Throwable {

    public MusicNotFoundException(String message) {
        super(message);
    }
}
