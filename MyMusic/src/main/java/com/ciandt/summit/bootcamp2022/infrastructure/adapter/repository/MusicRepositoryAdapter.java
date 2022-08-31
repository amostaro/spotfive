package com.ciandt.summit.bootcamp2022.infrastructure.adapter.repository;

import com.ciandt.summit.bootcamp2022.domain.data.dto.MusicDTO;
import com.ciandt.summit.bootcamp2022.domain.data.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.domain.port.repository.MusicRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.ArtistOrMusicNotFoundException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.MusicNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public MusicEntity findById(String idMusic) throws MusicNotFoundException {
        return springMusicRepository.findById(idMusic).orElseThrow(() -> new MusicNotFoundException("Musica não encontrada"));
    }
}
