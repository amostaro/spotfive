package com.ciandt.summit.bootcamp2022.domain.service.exception;

public class ArtistOrMusicNotFoundException extends Throwable {
    private static final String message = "Sua pesquisa com os parâmetros buscados não retornou nenhum artista ou música.";
    public ArtistOrMusicNotFoundException() {
        super(message);
    }
}
