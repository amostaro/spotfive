package com.ciandt.summit.bootcamp2022.domain.port.repository;

import com.ciandt.summit.bootcamp2022.domain.data.entity.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.domain.service.exception.PlaylistNotFoundException;

import java.util.Optional;

public interface PlaylistRepositoryPort {

    void savePlaylist(PlaylistEntity playlistEntity);

    Optional<PlaylistEntity> findById(String idPlayList) throws PlaylistNotFoundException;
}
