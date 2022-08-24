package com.ciandt.summit.bootcamp2022.domain.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDTO {

    @Schema(description = "Id do Artista")
    private String id;
    @Schema(description = "Nome do Artista")
    private String name;

}
