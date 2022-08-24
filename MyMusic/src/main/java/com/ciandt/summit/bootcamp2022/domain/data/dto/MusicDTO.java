package com.ciandt.summit.bootcamp2022.domain.data.dto;

import com.ciandt.summit.bootcamp2022.domain.data.entity.ArtistEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@EqualsAndHashCode
public class MusicDTO {

    private String id;

    private String name;

    @JsonProperty(value = "artist")
    private ArtistEntity artistEntity;

}
