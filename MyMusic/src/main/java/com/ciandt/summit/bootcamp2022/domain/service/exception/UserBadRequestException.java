package com.ciandt.summit.bootcamp2022.domain.service.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;

@Slf4j
public class UserBadRequestException extends Exception {

    private static final String MESSAGE = "Usuário não encontrado.";

    public UserBadRequestException () {
        super(MESSAGE);
        log.error("Processo finalizado com falha.");
        log.error("Usuário não encontrado em: " + Calendar.getInstance().getTime()+ ".");
    }
}
