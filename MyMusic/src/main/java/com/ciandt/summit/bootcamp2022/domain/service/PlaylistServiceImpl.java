package com.ciandt.summit.bootcamp2022.domain.service;

import com.ciandt.summit.bootcamp2022.domain.data.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.PlaylistServicePort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.MusicRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.PlaylistRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.MusicNotFoundException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.PlaylistNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistServicePort {

    private final PlaylistRepositoryPort playlistRepositoryPort;
    private final MusicRepositoryPort musicRepositoryPort;

    @Override
    public String saveMusicInPlaylist(String idPlaylist, String idMusic) throws PlaylistNotFoundException, MusicNotFoundException {
        log.info("Salvando musica na playlist");
        log.info("Buscando PlayList");
        PlaylistEntity playlistEntity = verifyIfPlaylistExists(idPlaylist);

        MusicEntity musicEntity = verifyIfMusicExists(idMusic);
        log.info("Buscando Musica");

        playlistEntity.getMusicEntityList().add(musicEntity);
        log.info("Pessoas buscadas com sucesso");

        playlistRepositoryPort.savePlaylist(playlistEntity);
        log.info("Musica salva com sucesso");

        return "Salvo com sucesso";
    }

    private MusicEntity verifyIfMusicExists(String idMusic) throws MusicNotFoundException {
        MusicEntity musicEntity = musicRepositoryPort.findById(idMusic)
                .orElseThrow(() -> new MusicNotFoundException("Musica não encontrada"));
        return musicEntity;
    }

    private PlaylistEntity verifyIfPlaylistExists(String idPlaylist) throws PlaylistNotFoundException {
        PlaylistEntity playlistEntity = playlistRepositoryPort.findById(idPlaylist)
                .orElseThrow(() -> new PlaylistNotFoundException("PlayList não foi encontrada"));
        return playlistEntity;
    }

}
