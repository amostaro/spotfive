package com.ciandt.summit.bootcamp2022.application.controller;

import com.ciandt.summit.bootcamp2022.domain.port.interfaces.PlaylistServicePort;
import com.ciandt.summit.bootcamp2022.domain.service.PlaylistServiceImpl;
import com.ciandt.summit.bootcamp2022.domain.service.exception.MusicNotFoundException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.PlaylistNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/playlists") //verificar URL
public class PlaylistController {

    private final PlaylistServicePort playlistServicePort;

    @PutMapping()
    public ResponseEntity<String> salvaMusic(@RequestParam String idMusica, String idPlaylist) throws MusicNotFoundException, PlaylistNotFoundException {
        return new ResponseEntity<>(playlistServicePort.saveMusicInPlaylist(idPlaylist,idMusica), HttpStatus.CREATED);
    }
}
