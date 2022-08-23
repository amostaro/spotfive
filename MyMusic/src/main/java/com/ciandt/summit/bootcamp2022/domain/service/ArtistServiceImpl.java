package com.ciandt.summit.bootcamp2022.domain.service;

import com.ciandt.summit.bootcamp2022.domain.data.dto.ArtistDTO;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.ArtistServicePort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.ArtistRepositoryPort;

import java.util.List;

public class ArtistServiceImpl implements ArtistServicePort {

    private final ArtistRepositoryPort artistRepositoryPort;

    public ArtistServiceImpl(ArtistRepositoryPort artistRepositoryPort) {
        this.artistRepositoryPort = artistRepositoryPort;
    }

    @Override
    public List<ArtistDTO> findByArtistOrMusic() {
        return null;
    }
}
