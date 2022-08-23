package com.ciandt.summit.bootcamp2022.domain.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MusicDTO {

    private String id;
    private String name;
    private ArtistDTO artistEntity;

}
