package com.ciandt.summit.bootcamp2022.domain.service.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;

@Slf4j
public class PlaylistIsNotOfUserException extends Exception {
    private static final String MESSAGE = "Playlist não pertence ao usuário.";
    public PlaylistIsNotOfUserException() {
        super(MESSAGE);
        log.error("Processo finalizado com falha.");
        log.error("Playlist não pertence ao usuário em: " + Calendar.getInstance().getTime()+ ".");
    }
}
