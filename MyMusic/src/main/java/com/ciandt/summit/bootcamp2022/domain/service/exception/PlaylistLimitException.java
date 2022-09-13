package com.ciandt.summit.bootcamp2022.domain.service.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;

@Slf4j
public class PlaylistLimitException extends Exception {
    private static final String MESSAGE = "Usuário do tipo COMUM, só podem ser adicionadas 5 músicas à sua playlist." +
            "Assine o PREMIUM e adicione quantas músicas quiser.";
    public PlaylistLimitException() {
        super(MESSAGE);
        log.error("Processo finalizado com falha.");
        log.error("Playlist com limite excedido em: " + Calendar.getInstance().getTime()+ ".");
    }
}
