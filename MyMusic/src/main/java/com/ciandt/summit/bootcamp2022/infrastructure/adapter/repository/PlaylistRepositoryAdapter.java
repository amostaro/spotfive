package com.ciandt.summit.bootcamp2022.infrastructure.adapter.repository;

import com.ciandt.summit.bootcamp2022.domain.data.entity.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.domain.port.repository.PlaylistRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.PlaylistNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PlaylistRepositoryAdapter implements PlaylistRepositoryPort {

    private final SpringPlaylistRepository springPlaylistRepository;
    @Override
    public void saveMusicInPlaylist(PlaylistEntity playlistEntity) {
        springPlaylistRepository.save(playlistEntity);
    }

    @Override
    public PlaylistEntity findById(String idPlayList) throws PlaylistNotFoundException {
        return springPlaylistRepository.findById(idPlayList).orElseThrow(() -> new PlaylistNotFoundException("PlayList não foi encontrada"));
    }
}
