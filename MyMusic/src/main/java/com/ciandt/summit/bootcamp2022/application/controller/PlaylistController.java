package com.ciandt.summit.bootcamp2022.application.controller;

import com.ciandt.summit.bootcamp2022.domain.port.interfaces.PlaylistServicePort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.MusicNotFoundException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.PlaylistNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}

