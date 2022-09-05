package com.ciandt.summit.bootcamp2022.domain.service.exception;

import lombok.extern.slf4j.Slf4j;
import java.util.Calendar;

@Slf4j
public class MusicNotInPlaylistException extends Throwable {

    private static final String message = "Música não encontrada na listagem da playlist informada.";
    public MusicNotInPlaylistException() {
        super(message);
        log.error("Processo finalizado com falha.");
        log.error("Música não encontrada na listagem da playlist em: " + Calendar.getInstance().getTime()+ ".");
    }
}
