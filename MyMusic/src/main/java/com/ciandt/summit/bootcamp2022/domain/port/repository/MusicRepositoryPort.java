package com.ciandt.summit.bootcamp2022.domain.port.repository;

import com.ciandt.summit.bootcamp2022.domain.data.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.domain.service.exception.MusicNotFoundException;

import java.util.List;
import java.util.Optional;

public interface MusicRepositoryPort {

    List<MusicEntity> findArtistEntityAndMusicEntityListOrderByName(String searchName);

    Optional<MusicEntity> findById(String idMusic) throws MusicNotFoundException;

}
