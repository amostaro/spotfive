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

import java.util.Calendar;

@Slf4j
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistServicePort {

    private final PlaylistRepositoryPort playlistRepositoryPort;
    private final MusicRepositoryPort musicRepositoryPort;

    @Override
    public String saveMusicInPlaylist(String idPlaylist, String idMusic) throws PlaylistNotFoundException, MusicNotFoundException {
        log.info("Iniciando processo de adição de uma música em uma playlist...");

        log.info("Busca da playlist '"+idPlaylist+"' iniciada em: " + Calendar.getInstance().getTime()+ ".");
        PlaylistEntity playlistEntity = playlistRepositoryPort.findById(idPlaylist);

        log.info("Busca da música '"+idMusic+"' iniciada em: " + Calendar.getInstance().getTime()+ ".");
        MusicEntity musicEntity = musicRepositoryPort.findById(idMusic);

        playlistEntity.getMusicEntityList().add(musicEntity);

        playlistRepositoryPort.saveMusicInPlaylist(playlistEntity);
        log.info("Processo finalizado.");
        log.info("Música '"+idMusic+"' adicionada à playlist '" +idPlaylist+ "' com sucesso em: " + Calendar.getInstance().getTime()+ ".");

        return "Música adicionada à playlist com sucesso!";
    }

}
