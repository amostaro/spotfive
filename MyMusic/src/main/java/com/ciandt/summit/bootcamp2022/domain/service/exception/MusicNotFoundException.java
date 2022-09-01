package com.ciandt.summit.bootcamp2022.domain.service.exception;

public class MusicNotFoundException extends Throwable {

    private static String message = "Música não encontrada na base de dados.";
    public MusicNotFoundException() {
        super(message);
    }
}
