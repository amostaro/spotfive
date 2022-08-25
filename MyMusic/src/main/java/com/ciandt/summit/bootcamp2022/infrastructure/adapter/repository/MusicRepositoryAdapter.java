package com.ciandt.summit.bootcamp2022.infrastructure.adapter.repository;

import com.ciandt.summit.bootcamp2022.domain.data.dto.ArtistDTO;
import com.ciandt.summit.bootcamp2022.domain.data.dto.MusicDTO;
import com.ciandt.summit.bootcamp2022.domain.data.entity.ArtistEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.domain.port.repository.ArtistRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.MusicRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.ArtistServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class MusicRepositoryAdapter implements MusicRepositoryPort {

    private final ModelMapper modelMapper;
    private final SpringMusicRepository springMusicRepository;

    @Override
    public List<MusicDTO> findArtistEntityAndMusicEntityListOrderByName(String searchName) {
        List<MusicEntity> allResultsByArtistOrMusic = springMusicRepository.findAllByNameLikeIgnoreCase(searchName);
        return allResultsByArtistOrMusic.stream()
                .map(music -> modelMapper.map(music, MusicDTO.class)).collect(Collectors.toList());
    }
}
