package com.ciandt.summit.bootcamp2022.domain.service.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;

@Slf4j
public class MusicNotFoundException extends Throwable {

    private static final String message = "Música não encontrada na base de dados.";
    public MusicNotFoundException() {
        super(message);
        log.error("Processo finalizado com falha.");
        log.error("Música não encontrada em: " + Calendar.getInstance().getTime()+ ".");
    }
}
