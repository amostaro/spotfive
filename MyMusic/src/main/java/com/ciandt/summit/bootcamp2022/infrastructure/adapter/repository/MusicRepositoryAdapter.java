package com.ciandt.summit.bootcamp2022.infrastructure.adapter.repository;

import com.ciandt.summit.bootcamp2022.domain.data.dto.MusicDTO;
import com.ciandt.summit.bootcamp2022.domain.data.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.domain.port.repository.MusicRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class MusicRepositoryAdapter implements MusicRepositoryPort {


    public ModelMapper modelMapper;


    private final SpringMusicRepository springMusicRepository;


    @Override
    public List<MusicDTO> findArtistEntityAndMusicEntityListOrderByName(String searchName) {
        List<MusicEntity> allByArtistOrMusic = springMusicRepository.findMusicOrderByNameLikeIgnoreCase(searchName);
        List<MusicDTO> musicDTO = allByArtistOrMusic.stream().map(music -> modelMapper.map(music, MusicDTO.class)).collect(Collectors.toList());

        return musicDTO;
    }
}
