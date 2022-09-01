package com.ciandt.summit.bootcamp2022.infrastructure.adapter.repository;

import com.ciandt.summit.bootcamp2022.domain.data.entity.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.domain.port.repository.PlaylistRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class PlaylistRepositoryAdapter implements PlaylistRepositoryPort {

    private final SpringPlaylistRepository springPlaylistRepository;
    @Override
    public void savePlaylist(PlaylistEntity playlistEntity) {
        springPlaylistRepository.save(playlistEntity);
    }

    @Override
    public Optional<PlaylistEntity> findById(String idPlayList) {
        return springPlaylistRepository.findById(idPlayList);
    }
}
