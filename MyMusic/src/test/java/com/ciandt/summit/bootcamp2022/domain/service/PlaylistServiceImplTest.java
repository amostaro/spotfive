package com.ciandt.summit.bootcamp2022.domain.service;

import com.ciandt.summit.bootcamp2022.domain.data.entity.*;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.UserServicePort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.MusicRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.PlaylistRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {PlaylistServiceImpl.class})
@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
class PlaylistServiceImplTest {

    @Autowired
    private PlaylistServiceImpl playlistService;

    @MockBean
    private PlaylistRepositoryPort playlistRepositoryPort;

    @MockBean
    private MusicRepositoryPort musicRepositoryPort;

    @MockBean
    private UserServicePort userServicePort;

    static String invalidId = "1234";
    static String playlistId = "92d8123f-e9f6-4806-8e0e-1c6a5d46f2ed";
    static String musicId = "c96b8f6f-4049-4e6b-8687-82e29c05b735";
    static String userId = "dd444a81-9588-4e6b-9d3d-1f1036a6eaa1";
    static String playlistNotFoundMessage = "Playlist não encontrada na base de dados.";
    static String musicLimitExceptionMessage = "Você atingiu o número máximo de músicas em sua playlist." +
            "Para adicionar mais músicas contrate o plano Premium.";
    static String musicNotFoundMessage = "Música não encontrada na base de dados.";
    static String musicNotInPlaylistMessage = "Música não encontrada na listagem da playlist informada.";

    @DisplayName("Should save music on playlist properly")
    @Test
    void shouldSaveMusicOnPlaylistProperly() throws MusicNotFoundException, PlaylistNotFoundException, UserNotFoundException, MusicLimitException, PlaylistNotFoundInUserException {

        ArtistEntity artistEntity = getArtistEntity();
        MusicEntity musicEntity = getMusicEntity(artistEntity);
        UserEntity userEntity = getUserEntity();
        PlaylistEntity playlistEntity = getPlaylistEntity(userEntity);
        userEntity.setPlaylistEntity(playlistEntity);

        when(this.playlistRepositoryPort.findById(any())).thenReturn(Optional.of(playlistEntity));
        when(this.musicRepositoryPort.findById(any())).thenReturn(Optional.of(musicEntity));

        when(this.userServicePort.userIsPremium(any())).thenReturn(true);
        when(!this.userServicePort.userIsPremium(any())).thenReturn(false);
        when(userServicePort.verifyIfUserExists(any())).thenReturn(userEntity);

        var response = playlistService.saveMusicInPlaylist(playlistId, musicId, userId);

        verify(playlistRepositoryPort, times(1)).savePlaylist(any(PlaylistEntity.class));

        assertEquals("Música adicionada à playlist com sucesso!", response);
    }

    @DisplayName("Should return playlist not found exception")
    @Test
    void shouldReturnPlaylistNotFoundException() {

        PlaylistNotFoundException playlistNotFoundException = assertThrows(PlaylistNotFoundException.class, () ->
                playlistService.verifyIfPlaylistExists(invalidId));

        assertEquals(playlistNotFoundMessage, playlistNotFoundException.getMessage());
    }

    @DisplayName("Should return music limit exception")
    @Test
    void shouldReturnMusicLimitException() throws MusicNotFoundException, MusicLimitException, PlaylistNotFoundException, UserNotFoundException {
        TipoUsuarioEntity tipoUsuarioEntity = new TipoUsuarioEntity();
        tipoUsuarioEntity.setId("mi561c28-4956-4k9c-3s4e-6l5461v3uio8");

        ArtistEntity artistEntity = getArtistEntity();
        MusicEntity musicEntity = getMusicEntity(artistEntity);
        MusicEntity musicEntity2 = getMusicEntity(artistEntity);
        MusicEntity musicEntity3 = getMusicEntity(artistEntity);
        MusicEntity musicEntity4 = getMusicEntity(artistEntity);
        MusicEntity musicEntity5 = getMusicEntity(artistEntity);

        UserEntity userEntity = getUserEntity();
        userEntity.setTipoUsuarioEntity(tipoUsuarioEntity);

        PlaylistEntity playlistEntity = getPlaylistEntity(userEntity);
        userEntity.setPlaylistEntity(playlistEntity);

        playlistEntity.getMusicEntityList().add(musicEntity);
        playlistEntity.getMusicEntityList().add(musicEntity2);
        playlistEntity.getMusicEntityList().add(musicEntity3);
        playlistEntity.getMusicEntityList().add(musicEntity4);
        playlistEntity.getMusicEntityList().add(musicEntity5);

        when(this.playlistRepositoryPort.findById(playlistId)).thenReturn(Optional.of(playlistEntity));
        when(this.musicRepositoryPort.findById(musicId)).thenReturn(Optional.of(musicEntity));
        when(userServicePort.verifyIfUserExists(any())).thenReturn(userEntity);

        MusicLimitException musicLimitException = assertThrows(MusicLimitException.class, () ->
                playlistService.saveMusicInPlaylist(playlistId, musicId, userId));

        assertEquals(musicLimitExceptionMessage, musicLimitException.getMessage());
    }

    @DisplayName("Should return music not found exception")
    @Test
    void shouldReturnMusicNotFoundException() {

        MusicNotFoundException musicNotFoundException = assertThrows(MusicNotFoundException.class, () ->
                playlistService.verifyIfMusicExists(invalidId));

        assertEquals(musicNotFoundMessage, musicNotFoundException.getMessage());
    }

    @DisplayName("Should remove music of playlist properly")
    @Test
    void shouldRemoveMusicOfPlaylistProperly() throws MusicNotFoundException, PlaylistNotFoundException,
            MusicNotInPlaylistException {

        ArtistEntity artistEntity = getArtistEntity();
        MusicEntity musicEntity = getMusicEntity(artistEntity);
        UserEntity userEntity = getUserEntity();
        PlaylistEntity playlistEntity = getPlaylistEntity(userEntity);

        playlistEntity.getMusicEntityList().add(musicEntity);

        when(playlistRepositoryPort.findById(any())).thenReturn(Optional.of(playlistEntity));
        when(musicRepositoryPort.findById(any())).thenReturn(Optional.of(musicEntity));

        playlistService.deleteMusicInPlaylist("1", "1");

        verify(playlistRepositoryPort, times(1)).savePlaylist(any(PlaylistEntity.class));
    }

    @DisplayName("Should return music not in playlist exception")
    @Test
    void shouldReturnMusicNotInPlaylistException() {

        ArtistEntity artistEntity = getArtistEntity();
        MusicEntity musicEntity = getMusicEntity(artistEntity);
        UserEntity userEntity = getUserEntity();
        PlaylistEntity playlistEntity = getPlaylistEntity(userEntity);

        playlistEntity.getMusicEntityList().add(musicEntity);

        MusicEntity invalidMusicEntity = getMusicEntity(artistEntity);
        invalidMusicEntity.setId(invalidId);

        MusicNotInPlaylistException musicNotInPlaylistException = assertThrows(MusicNotInPlaylistException.class,
                () -> playlistService.verifyIfMusicExistsInPlaylist(playlistEntity, invalidMusicEntity));

        assertEquals(musicNotInPlaylistMessage, musicNotInPlaylistException.getMessage());

    }

    private static MusicEntity getMusicEntity(ArtistEntity artistEntity) {
        MusicEntity musicEntity = new MusicEntity();
        musicEntity.setArtistEntity(artistEntity);
        musicEntity.setId("1");
        musicEntity.setName("Matheus");
        return musicEntity;
    }

    private static MusicEntity getMusicEntity2(ArtistEntity artistEntity) {
        MusicEntity musicEntity2 = new MusicEntity();
        musicEntity2.setArtistEntity(artistEntity);
        musicEntity2.setId("2");
        musicEntity2.setName("Matheus");
        return musicEntity2;
    }

    private static MusicEntity getMusicEntity3(ArtistEntity artistEntity) {
        MusicEntity musicEntity3 = new MusicEntity();
        musicEntity3.setArtistEntity(artistEntity);
        musicEntity3.setId("3");
        musicEntity3.setName("Matheus");
        return musicEntity3;
    }

    private static MusicEntity getMusicEntity4(ArtistEntity artistEntity) {
        MusicEntity musicEntity4 = new MusicEntity();
        musicEntity4.setArtistEntity(artistEntity);
        musicEntity4.setId("4");
        musicEntity4.setName("Matheus");
        return musicEntity4;
    }

    private static MusicEntity getMusicEntity5(ArtistEntity artistEntity) {
        MusicEntity musicEntity5 = new MusicEntity();
        musicEntity5.setArtistEntity(artistEntity);
        musicEntity5.setId("5");
        musicEntity5.setName("Matheus");
        return musicEntity5;
    }

    private static PlaylistEntity getPlaylistEntity(UserEntity userEntity) {
        PlaylistEntity playlistEntity = new PlaylistEntity();
        playlistEntity.setMusicEntityList(new HashSet<>());
        playlistEntity.setId("1");
        playlistEntity.setUserEntityList(Set.of(userEntity));
        return playlistEntity;
    }

    private static UserEntity getUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("1");
        userEntity.setName("André");
        return userEntity;
    }

    private static ArtistEntity getArtistEntity() {
        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setId("1");
        artistEntity.setName("Eric");
        return artistEntity;
    }
}
