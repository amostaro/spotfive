package com.ciandt.summit.bootcamp2022.infrastructure.adapter.repository;

import com.ciandt.summit.bootcamp2022.domain.data.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.domain.port.repository.MusicRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class MusicRepositoryAdapter implements MusicRepositoryPort {

    private final SpringMusicRepository springMusicRepository;

    @Override
    public List<MusicEntity> findArtistEntityAndMusicEntityListOrderByName(String searchName) {
        return springMusicRepository.findAllByNameLikeIgnoreCase(searchName);
    }

    @Override
    public Optional<MusicEntity> findById(String idMusic) {
        return springMusicRepository.findById(idMusic);
    }
}
