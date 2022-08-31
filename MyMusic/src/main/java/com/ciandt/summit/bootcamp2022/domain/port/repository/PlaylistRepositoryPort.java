package com.ciandt.summit.bootcamp2022.domain.port.repository;

import com.ciandt.summit.bootcamp2022.domain.data.entity.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.domain.service.exception.PlaylistNotFoundException;

public interface PlaylistRepositoryPort {

    void saveMusicInPlaylist(PlaylistEntity playlistEntity);

    PlaylistEntity findById(String idPlayList) throws PlaylistNotFoundException;
}
