package com.ciandt.summit.bootcamp2022.application.controller;

import com.ciandt.summit.bootcamp2022.domain.port.interfaces.PlaylistServicePort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.MusicNotFoundException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.PlaylistNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {
    private final PlaylistServicePort playlistServicePort;

    @PutMapping("/{idPlaylist}/musicas")
    public ResponseEntity<String> updatePlaylist(@RequestParam String idMusica, @PathVariable String idPlaylist) throws MusicNotFoundException, PlaylistNotFoundException {

        String updated = playlistServicePort.saveMusicInPlaylist(idPlaylist,idMusica);

        return new ResponseEntity<>(updated, HttpStatus.CREATED);
    }

}

