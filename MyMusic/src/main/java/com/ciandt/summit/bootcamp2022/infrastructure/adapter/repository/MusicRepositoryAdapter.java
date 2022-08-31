package com.ciandt.summit.bootcamp2022.infrastructure.adapter.repository;

import com.ciandt.summit.bootcamp2022.domain.data.dto.MusicDTO;
import com.ciandt.summit.bootcamp2022.domain.data.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.domain.port.repository.MusicRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.MusicNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
        log.info("Processo finalizado com falha.");
        log.info("Música '"+idMusic+"' não encontrada em: " + Calendar.getInstance().getTime()+ ".");
        return springMusicRepository.findById(idMusic).orElseThrow(() -> new MusicNotFoundException("Musica não encontrada"));

    }
}
