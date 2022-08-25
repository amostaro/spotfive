package com.ciandt.summit.bootcamp2022.domain.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class MusicDTO implements Serializable {

    @Schema(description = "Id da música")
    private String id;

    @Schema(description = "Nome da música")
    private String name;

    @JsonProperty(value = "artist")
    @Schema(description = "Autor da música")
    private ArtistDTO artistEntity;

}
