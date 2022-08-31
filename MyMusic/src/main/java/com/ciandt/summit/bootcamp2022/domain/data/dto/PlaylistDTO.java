package com.ciandt.summit.bootcamp2022.domain.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PlaylistDTO implements Serializable {

    @Schema(description = "Id da Playlist")
    private String id;

    @Schema(description = "Lista de Usuários da Playlist")
    private Set<UserDTO> userEntityList;

    @Schema(description = "Lista de Músicas da Playlist")
    private Set<MusicDTO> musicEntityList;
}
