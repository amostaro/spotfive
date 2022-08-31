package com.ciandt.summit.bootcamp2022.infrastructure.adapter.repository;

import com.ciandt.summit.bootcamp2022.domain.data.entity.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.domain.port.repository.PlaylistRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.PlaylistNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Slf4j
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
        log.info("Processo finalizado com falha.");
        log.info("Playlist '"+idPlayList+"' não encontrada em: " + Calendar.getInstance().getTime()+ ".");
        return springPlaylistRepository.findById(idPlayList).orElseThrow(() -> new PlaylistNotFoundException("PlayList não foi encontrada"));
    }
}
