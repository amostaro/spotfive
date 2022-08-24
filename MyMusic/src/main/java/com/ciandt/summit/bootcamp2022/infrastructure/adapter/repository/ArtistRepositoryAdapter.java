package com.ciandt.summit.bootcamp2022.infrastructure.adapter.repository;

import com.ciandt.summit.bootcamp2022.domain.data.dto.ArtistDTO;
import com.ciandt.summit.bootcamp2022.domain.data.entity.ArtistEntity;
import com.ciandt.summit.bootcamp2022.domain.port.repository.ArtistRepositoryPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ArtistRepositoryAdapter implements ArtistRepositoryPort {


    private final ObjectMapper objectMapper;


    private final SpringArtistRepository springArtistRepository;


    @Override
    public List<ArtistDTO> findArtistEntityAndMusicEntityListOrderByName(String searchName) {
        List<ArtistEntity> allResultsByArtistOrMusic = springArtistRepository.findAllByNameLikeIgnoreCase(searchName);
        return allResultsByArtistOrMusic.stream().map(artist -> objectMapper.convertValue(artist, ArtistDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ArtistDTO findById(String id) {
        return objectMapper.convertValue(springArtistRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Não encontrado")), ArtistDTO.class);
        //arrumar exception
    }
}
