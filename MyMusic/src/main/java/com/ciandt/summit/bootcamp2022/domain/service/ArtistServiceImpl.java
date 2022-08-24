package com.ciandt.summit.bootcamp2022.domain.service;

import com.ciandt.summit.bootcamp2022.domain.data.dto.ArtistDTO;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.ArtistServicePort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.ArtistRepositoryPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistServicePort {

    private final ArtistRepositoryPort artistRepositoryPort;

    @Override
    public List<ArtistDTO> findAllByNameLikeIgnoreCase(String searchName) {
        return this.artistRepositoryPort.findArtistEntityAndMusicEntityListOrderByName(searchName);
    }

    public ArtistDTO findAllArtistById(String id){
        return artistRepositoryPort.findById(id);
    }

}
