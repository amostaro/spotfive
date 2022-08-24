package com.ciandt.summit.bootcamp2022.domain.port.repository;

import com.ciandt.summit.bootcamp2022.domain.data.dto.ArtistDTO;

import java.util.List;

public interface ArtistRepositoryPort {

    List<ArtistDTO> findArtistEntityAndMusicEntityListOrderByName(String searchName);

    ArtistDTO findById(String id);

}
