package com.ciandt.summit.bootcamp2022.domain.service.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;

@Slf4j
public class PlaylistNotFoundInUserException extends Exception {

    private static final String MESSAGE = "Playlist não e do usuario";
    public PlaylistNotFoundInUserException() {
        super(MESSAGE);
        log.error("Processo finalizado com falha.");
        log.error("Playlist não encontrada em: " + Calendar.getInstance().getTime()+ ".");
    }
}
