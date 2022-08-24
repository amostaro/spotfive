package com.ciandt.summit.bootcamp2022.domain.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Set;

@Data
public class DataDTO {

    @Schema(description = "Lista de dados")
    Set<MusicDTO> data;
}
