package com.ciandt.summit.bootcamp2022.domain.service;

import com.ciandt.summit.bootcamp2022.domain.data.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.PlaylistServicePort;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.UserServicePort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.MusicRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.PlaylistRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.MusicNotFoundException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.MusicNotInPlaylistException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.PlaylistNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;

@Slf4j
public class PlaylistServiceImpl implements PlaylistServicePort {

    public static final String INICIADA_EM = "' iniciada em: ";
    private final PlaylistRepositoryPort playlistRepositoryPort;
    private final MusicRepositoryPort musicRepositoryPort;

    public PlaylistServiceImpl(PlaylistRepositoryPort playlistRepositoryPort, MusicRepositoryPort musicRepositoryPort) {
        this.playlistRepositoryPort = playlistRepositoryPort;
        this.musicRepositoryPort = musicRepositoryPort;
    }

    @Autowired
    private UserServicePort userServicePort;

    @Override
    public String saveMusicInPlaylist(String idPlaylist, String idMusic, String userId) throws PlaylistNotFoundException, MusicNotFoundException {

        log.info("Iniciando processo de adição de uma música em uma playlist...");

        log.info("Busca da playlist '" + idPlaylist + INICIADA_EM + Calendar.getInstance().getTime() + ".");
        PlaylistEntity playlistEntity = verifyIfPlaylistExists(idPlaylist);

        log.info("Busca da música '" + idMusic + INICIADA_EM + Calendar.getInstance().getTime() + ".");
        MusicEntity musicEntity = verifyIfMusicExists(idMusic);

        if (userServicePort.userIsPremium(userId) || (userServicePort.userIsPremium(userId) == false && playlistEntity.getMusicEntityList().size() <= 5)) {
            playlistEntity.getMusicEntityList().add(musicEntity);

            playlistRepositoryPort.savePlaylist(playlistEntity);

            log.info("Processo finalizado.");
            log.info("Música '" + idMusic + "' adicionada à playlist '" + idPlaylist + "' com sucesso em: " + Calendar.getInstance().getTime() + ".");

            return "Música adicionada à playlist com sucesso!";

        }

        log.info("Processo finalizado sem sucesso, o usuário atingiu o número máximo de músicas na lista, permitida " +
                "no seu plano. Finalizado em: " + Calendar.getInstance().getTime() + ".");

        return "Você atingiu o número máximo de músicas em sua playlist. Para adicionar mais músicas contrate o plano" +
                " Premium.";

    }

    @Override
    public void deleteMusicInPlaylist(String idPlaylist, String idMusic) throws PlaylistNotFoundException,
            MusicNotFoundException, MusicNotInPlaylistException {

        log.info("Iniciando processo de remoção de uma música de uma playlist...");

        log.info("Busca da playlist '" + idPlaylist + INICIADA_EM + Calendar.getInstance().getTime() + ".");
        PlaylistEntity playlistEntity = verifyIfPlaylistExists(idPlaylist);

        log.info("Busca da música '" + idMusic + INICIADA_EM + Calendar.getInstance().getTime() + ".");
        MusicEntity musicEntity = verifyIfMusicExists(idMusic);

        verifyIfMusicExistsInPlaylist(playlistEntity, musicEntity);

        playlistEntity.getMusicEntityList().remove(musicEntity);

        playlistRepositoryPort.savePlaylist(playlistEntity);

        log.info("Processo finalizado.");
        log.info("Música '" + idMusic + "' removida da playlist '" + idPlaylist + "' com sucesso em: " + Calendar.getInstance().getTime() + ".");

    }

    public void verifyIfMusicExistsInPlaylist(PlaylistEntity playlistEntity, MusicEntity musicEntity) throws MusicNotInPlaylistException {

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
