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
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {PlaylistServiceImpl.class})
@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
class PlaylistServiceImplTest {

    @Autowired
    @InjectMocks
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

    static String playlistNotFoundInUserMessage = "Playlist não é do usuario";
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

    @DisplayName("Should save music on playlist properly")
    @Test
    void shouldSaveMusicInPremium() throws MusicNotFoundException, PlaylistNotFoundException, UserNotFoundException, MusicLimitException, PlaylistNotFoundInUserException {

        ArtistEntity artistEntity = getArtistEntity();
        MusicEntity musicEntity = getMusicEntity(artistEntity);
        UserEntity userEntity = getUserEntity();
        PlaylistEntity playlistEntity = getPlaylistEntity(userEntity);
        userEntity.setPlaylistEntity(playlistEntity);

        when(this.playlistRepositoryPort.findById(any())).thenReturn(Optional.of(playlistEntity));
        when(this.musicRepositoryPort.findById(any())).thenReturn(Optional.of(musicEntity));

        when(this.userServicePort.userIsPremium(any())).thenReturn(false);
        when(!this.userServicePort.userIsPremium(any())).thenReturn(true);
        when(userServicePort.verifyIfUserExists(any())).thenReturn(userEntity);

        var response = playlistService.saveMusicInPlaylist(playlistId, musicId, userId);

        assertEquals("Música adicionada à playlist com sucesso!", response);
    }

    @DisplayName("Should save music on playlist properly")
    @Test
    void shouldSaveMusicInPremiumFalse() throws MusicNotFoundException, PlaylistNotFoundException, UserNotFoundException, MusicLimitException, PlaylistNotFoundInUserException {

        ArtistEntity artistEntity = getArtistEntity();
        MusicEntity musicEntity = getMusicEntity(artistEntity);
        UserEntity userEntity = getUserEntity();
        PlaylistEntity playlistEntity = getPlaylistEntity(userEntity);
        userEntity.setPlaylistEntity(playlistEntity);

        playlistEntity.getMusicEntityList().add(musicEntity);


        when(this.playlistRepositoryPort.findById(any())).thenReturn(Optional.of(playlistEntity));
        when(this.musicRepositoryPort.findById(any())).thenReturn(Optional.of(musicEntity));

        when(this.userServicePort.userIsPremium(any())).thenReturn(false);
        when(!this.userServicePort.userIsPremium(any())).thenReturn(false);
        when(userServicePort.verifyIfUserExists(any())).thenReturn(userEntity);

        var response = playlistService.saveMusicInPlaylist(playlistId, musicId, userId);

        verify(userServicePort, times(1)).verifyIfUserExists(any());
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
    void shouldReturnMusicLimitException() throws MusicNotFoundException, UserNotFoundException {
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

    @DisplayName("Should return playlist not found exception")
    @Test
    void shouldReturnPlaylistNotFoundInUserException() throws UserNotFoundException {
        ArtistEntity artistEntity = getArtistEntity();
        MusicEntity musicEntity = getMusicEntity(artistEntity);
        MusicEntity musicEntity2 = getMusicEntity(artistEntity);
        MusicEntity musicEntity3 = getMusicEntity(artistEntity);
        MusicEntity musicEntity4 = getMusicEntity(artistEntity);
        PlaylistEntity playlistEntity2 = new PlaylistEntity();
        UserEntity userEntity = getUserEntity();
        PlaylistEntity playlistEntity = getPlaylistEntity(userEntity);
        userEntity.setPlaylistEntity(playlistEntity2);

        playlistEntity.getMusicEntityList().add(musicEntity);
        playlistEntity.getMusicEntityList().add(musicEntity2);
        playlistEntity.getMusicEntityList().add(musicEntity3);
        playlistEntity.getMusicEntityList().add(musicEntity4);

        when(userServicePort.verifyIfUserExists(any())).thenReturn(userEntity);

        PlaylistNotFoundInUserException playlistNotFoundInUserException = assertThrows(PlaylistNotFoundInUserException.class, () ->
                playlistService.verifyIfPlaylistInUser(userId,playlistEntity));

        assertEquals(playlistNotFoundInUserMessage, playlistNotFoundInUserException.getMessage());
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
