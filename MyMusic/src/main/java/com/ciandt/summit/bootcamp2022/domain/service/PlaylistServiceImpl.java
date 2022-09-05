package com.ciandt.summit.bootcamp2022.domain.service;

import com.ciandt.summit.bootcamp2022.domain.data.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.PlaylistServicePort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.MusicRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.PlaylistRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.MusicNotFoundException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.MusicNotInPlaylistException;
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
        PlaylistEntity playlistEntity = verifyIfPlaylistExists(idPlaylist);

        log.info("Busca da música '"+idMusic+"' iniciada em: " + Calendar.getInstance().getTime()+ ".");
        MusicEntity musicEntity = verifyIfMusicExists(idMusic);

        playlistEntity.getMusicEntityList().add(musicEntity);

        playlistRepositoryPort.savePlaylist(playlistEntity);

        log.info("Processo finalizado.");
        log.info("Música '"+idMusic+"' adicionada à playlist '" +idPlaylist+ "' com sucesso em: " + Calendar.getInstance().getTime()+ ".");

        return "Música adicionada à playlist com sucesso!";
    }

    @Override
    public void deleteMusicInPlaylist(String idPlaylist, String idMusic) throws PlaylistNotFoundException, MusicNotFoundException, MusicNotInPlaylistException {

        log.info("Iniciando processo de remoção de uma música de uma playlist...");

        log.info("Busca da playlist '"+idPlaylist+"' iniciada em: " + Calendar.getInstance().getTime()+ ".");
        PlaylistEntity playlistEntity = verifyIfPlaylistExists(idPlaylist);

        log.info("Busca da música '"+idMusic+"' iniciada em: " + Calendar.getInstance().getTime()+ ".");
        MusicEntity musicEntity = verifyIfMusicExists(idMusic);

        verifyIfMusicExistsInPlaylist(playlistEntity, musicEntity);

        playlistEntity.getMusicEntityList().remove(musicEntity);

        playlistRepositoryPort.savePlaylist(playlistEntity);

        log.info("Processo finalizado.");
        log.info("Música '"+idMusic+"' removida da playlist '" +idPlaylist+ "' com sucesso em: " + Calendar.getInstance().getTime()+ ".");

    }

    private static void verifyIfMusicExistsInPlaylist(PlaylistEntity playlistEntity, MusicEntity musicEntity) throws MusicNotInPlaylistException {
        if (!playlistEntity.getMusicEntityList().contains(musicEntity)) {
            throw new MusicNotInPlaylistException();
        }
    }

    public MusicEntity verifyIfMusicExists(String idMusic) throws MusicNotFoundException {
        return musicRepositoryPort.findById(idMusic)
                .orElseThrow(MusicNotFoundException::new);
    }

    public PlaylistEntity verifyIfPlaylistExists(String idPlaylist) throws PlaylistNotFoundException {
        return playlistRepositoryPort.findById(idPlaylist)
                .orElseThrow(PlaylistNotFoundException::new);
    }
}
