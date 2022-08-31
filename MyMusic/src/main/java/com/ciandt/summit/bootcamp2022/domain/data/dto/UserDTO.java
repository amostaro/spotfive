package com.ciandt.summit.bootcamp2022.domain.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class UserDTO implements Serializable {

    @Schema(description = "Id do Usuário")
    private String id;

    @Schema(description = "Nome do Usuário")
    private String name;

    @Schema(description = "Playlist do Usuário")
    private PlaylistDTO playlistEntity;

}
