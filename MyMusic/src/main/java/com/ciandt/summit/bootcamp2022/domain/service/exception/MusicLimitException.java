package com.ciandt.summit.bootcamp2022.domain.service.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;

@Slf4j
public class MusicLimitException extends Exception {
    private static final String MESSAGE = "Você atingiu o número máximo de músicas em sua playlist." +
            "Para adicionar mais músicas contrate o plano Premium.";
    public MusicLimitException() {
        super(MESSAGE);
        log.info("Processo finalizado sem sucesso, o usuário atingiu o número máximo de músicas na lista, permitida " +
                "no seu plano. Finalizado em: " + Calendar.getInstance().getTime() + ".");
    }
}
