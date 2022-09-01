package com.ciandt.summit.bootcamp2022.domain.service.exception;

public class PlaylistNotFoundException extends Throwable {
    private static String message = "Playlist não encontrada na base de dados.";
    public PlaylistNotFoundException() {
        super(message);
    }
}
