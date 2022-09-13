package com.ciandt.summit.bootcamp2022.domain.service;

import com.ciandt.summit.bootcamp2022.domain.data.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.UserEntity;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.PlaylistServicePort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.MusicRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.PlaylistRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.UserRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistServicePort {

    public static final String INICIADA_EM = "' iniciada em: ";
    private final PlaylistRepositoryPort playlistRepositoryPort;
    private final MusicRepositoryPort musicRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public String saveMusicInPlaylist(String idPlaylist, String idMusic) throws PlaylistNotFoundException, MusicNotFoundException {

        log.info("Iniciando processo de adição de uma música em uma playlist...");

        log.info("Busca da playlist '" + idPlaylist + INICIADA_EM + Calendar.getInstance().getTime() + ".");
        PlaylistEntity playlistEntity = verifyIfPlaylistExists(idPlaylist);

        log.info("Busca da música '" + idMusic + INICIADA_EM + Calendar.getInstance().getTime() + ".");
        MusicEntity musicEntity = verifyIfMusicExists(idMusic);

        playlistEntity.getMusicEntityList().add(musicEntity);

        playlistRepositoryPort.savePlaylist(playlistEntity);

        log.info("Processo finalizado.");
        log.info("Música '" + idMusic + "' adicionada à playlist '" + idPlaylist + "' com sucesso em: " + Calendar.getInstance().getTime() + ".");

        return "Música adicionada à playlist com sucesso!";
    }

    @Override
    public void deleteMusicInPlaylist(String idPlaylist, String idMusic) throws PlaylistNotFoundException, MusicNotFoundException, MusicNotInPlaylistException {

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

    @Override
    public String saveMusicInUsersPlaylist(String idPlaylist, String idMusic, String userId) throws
            PlaylistNotFoundException, MusicNotFoundException, PlaylistIsNotOfUserException, PlaylistLimitException {

        PlaylistEntity playlistEntity = verifyIfPlaylistExists(idPlaylist);
        MusicEntity musicEntity = verifyIfMusicExists(idMusic);

        Optional<UserEntity> userEntity = userRepositoryPort.findById(userId);
        if (userEntity.get().getPlaylistId().equals(idPlaylist)) {
            if (Objects.equals(userEntity.get().getTipoUsuarioId(), "mi561c28-4956-4k9c-3s4e-6l5461v3uio8")) {
                if (userEntity.get().getPlaylistEntity().getMusicEntityList().size() < 5) {
                    userEntity.get().getPlaylistEntity().getMusicEntityList().add(musicEntity);
                    playlistRepositoryPort.savePlaylist(playlistEntity);
                } else {
                    throw new PlaylistLimitException();
                }
            }
            if (Objects.equals(userEntity.get().getTipoUsuarioId(), "sa764b91-1235-2s9x-2k4e-2s5687x4lco2")) {
                userEntity.get().getPlaylistEntity().getMusicEntityList().add(musicEntity);
                playlistRepositoryPort.savePlaylist(playlistEntity);
            }
        } else {
            throw new PlaylistIsNotOfUserException();
        }

        return "Música adicionada à playlist com sucesso!";
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
