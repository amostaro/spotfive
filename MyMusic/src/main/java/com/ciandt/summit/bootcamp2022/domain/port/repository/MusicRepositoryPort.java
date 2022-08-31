package com.ciandt.summit.bootcamp2022.domain.port.repository;

import com.ciandt.summit.bootcamp2022.domain.data.dto.MusicDTO;
import com.ciandt.summit.bootcamp2022.domain.data.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.domain.service.exception.MusicNotFoundException;

import java.util.List;

public interface MusicRepositoryPort {

    List<MusicDTO> findArtistEntityAndMusicEntityListOrderByName(String searchName);

    MusicEntity findById(String idMusic) throws MusicNotFoundException;

}
