package com.ciandt.summit.bootcamp2022.domain.service.exception;

public class ArtistOrMusicNotFoundException extends Exception {
    private static final String MESSAGE = "Sua pesquisa com os parâmetros buscados não retornou nenhum artista ou música.";
    public ArtistOrMusicNotFoundException() {
        super(MESSAGE);
    }
}
