package com.ciandt.summit.bootcamp2022.infrastructure.adapter.repository;

import com.ciandt.summit.bootcamp2022.domain.data.dto.MusicDTO;
import com.ciandt.summit.bootcamp2022.domain.data.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.domain.port.repository.MusicRepositoryPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class MusicRepositoryAdapter implements MusicRepositoryPort {


    private final ObjectMapper modelMapper;


    private final SpringMusicRepository springMusicRepository;


    @Override
    public List<MusicDTO> findArtistEntityAndMusicEntityListOrderByName(String searchName) {
        List<MusicEntity> allResultsByArtistOrMusic = springMusicRepository.findAllByNameLikeIgnoreCase(searchName);
        return allResultsByArtistOrMusic.stream().map(music -> objectMapper.convertValue(music, MusicDTO.class)).collect(Collectors.toList());
    }
}
