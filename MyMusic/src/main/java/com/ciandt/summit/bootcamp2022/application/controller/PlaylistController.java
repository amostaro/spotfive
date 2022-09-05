package com.ciandt.summit.bootcamp2022.application.controller;

import com.ciandt.summit.bootcamp2022.domain.port.interfaces.PlaylistServicePort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.MusicNotFoundException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.MusicNotInPlaylistException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.PlaylistNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ApiResponses(
        value = {
                @ApiResponse(responseCode = "200", description = "Requisição feita com sucesso"),
                @ApiResponse(responseCode = "401", description = "Você não tem permissão para acessar este recurso"),
                @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
                @ApiResponse(responseCode = "204", description = "Não foi encontrado nenhum dado")
        }
)

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {
    private final PlaylistServicePort playlistServicePort;

    @Operation(description = "Realiza a busca de uma playlist pelo seu id, e adiciona uma música na lista, de acordo com o id da música informado.")
    @PutMapping("/{idPlaylist}/musicas")
    public ResponseEntity<String> addMusicInPlaylist(@RequestParam String idMusica, @PathVariable String idPlaylist) throws MusicNotFoundException, PlaylistNotFoundException {

        String updated = playlistServicePort.saveMusicInPlaylist(idPlaylist, idMusica);

        return new ResponseEntity<>(updated, HttpStatus.CREATED);
    }

    @Operation(description = "Realiza a busca de uma playlist e música pelo seu id, verifica se a música esta na listagem da playlist e remove a música da lista, de acordo com o id da música informado.")
    @DeleteMapping("/{idPlaylist}/musicas/{musicaId}")
    public ResponseEntity<String> removeMusicInPlaylist(@PathVariable String musicaId, @PathVariable String idPlaylist) throws MusicNotFoundException, PlaylistNotFoundException, MusicNotInPlaylistException {

        playlistServicePort.deleteMusicInPlaylist(idPlaylist, musicaId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

