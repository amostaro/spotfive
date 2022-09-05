package com.ciandt.summit.bootcamp2022.domain.service.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;

@Slf4j
public class PlaylistNotFoundException extends Exception {
    private static final String MESSAGE = "Playlist não encontrada na base de dados.";
    public PlaylistNotFoundException() {
        super(MESSAGE);
        log.error("Processo finalizado com falha.");
        log.error("Playlist não encontrada em: " + Calendar.getInstance().getTime()+ ".");
    }
}
