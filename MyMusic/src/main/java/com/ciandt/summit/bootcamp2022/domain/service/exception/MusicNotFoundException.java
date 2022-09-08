package com.ciandt.summit.bootcamp2022.domain.service.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;

@Slf4j
public class MusicNotFoundException extends Exception {

    private static final String MESSAGE = "Música não encontrada na base de dados.";
    public MusicNotFoundException() {
        super(MESSAGE);
        log.error("Processo finalizado com falha.");
        log.error("Música não encontrada em: " + Calendar.getInstance().getTime()+ ".");
    }
}
