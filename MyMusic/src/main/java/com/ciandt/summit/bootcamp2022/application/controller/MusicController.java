package com.ciandt.summit.bootcamp2022.application.controller;

import com.ciandt.summit.bootcamp2022.domain.data.dto.DataDTO;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.MusicServicePort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.ArtistOrMusicNotFoundException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.LengthValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/api/musicas")
public class MusicController {

    private final MusicServicePort musicServicePort;

    @Operation(description = "Realiza a busca de todos os artistas ou músicas com os parâmetros informados")
    @GetMapping
    public ResponseEntity<DataDTO> getArtistOrMusic(@RequestParam String filtro) throws LengthValidationException,
            ArtistOrMusicNotFoundException {
        DataDTO dataDTO = musicServicePort.findAllByNameLikeIgnoreCase(filtro);
        return new ResponseEntity<>(dataDTO, HttpStatus.OK);
    }

}
