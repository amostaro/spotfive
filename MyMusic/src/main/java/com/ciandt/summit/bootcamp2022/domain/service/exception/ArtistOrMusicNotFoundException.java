package com.ciandt.summit.bootcamp2022.domain.service.exception;

public class ArtistOrMusicNotFoundException extends Throwable {
    public ArtistOrMusicNotFoundException(String message) {
        super(message);
    }
}
