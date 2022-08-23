package com.ciandt.summit.bootcamp2022.domain.port.repository;

import com.ciandt.summit.bootcamp2022.domain.data.dto.MusicDTO;

import java.util.List;

public interface MusicRepositoryPort {

    List<MusicDTO> findArtistEntityAndMusicEntityListOrderByName(String searchName);

}
