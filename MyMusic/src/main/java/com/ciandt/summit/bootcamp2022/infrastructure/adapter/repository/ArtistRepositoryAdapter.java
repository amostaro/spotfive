package com.ciandt.summit.bootcamp2022.infrastructure.adapter.repository;

import com.ciandt.summit.bootcamp2022.domain.data.dto.ArtistDTO;
import com.ciandt.summit.bootcamp2022.domain.port.repository.ArtistRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArtistRepositoryAdapter implements ArtistRepositoryPort {

    private final SpringArtistRepository springArtistRepository;

    public ArtistRepositoryAdapter(SpringArtistRepository springArtistRepository) {
        this.springArtistRepository = springArtistRepository;
    }

    @Override
    public List<ArtistDTO> findByArtistOrMusic() {
        return null;
    }
}
