package com.ciandt.summit.bootcamp2022.domain.service;

import com.ciandt.summit.bootcamp2022.domain.data.entity.ArtistEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.UserEntity;
import com.ciandt.summit.bootcamp2022.domain.port.repository.MusicRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.PlaylistRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.MusicNotFoundException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.MusicNotInPlaylistException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.PlaylistNotFoundException;
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
    private PlaylistServiceImpl service;

    @MockBean
    private PlaylistRepositoryPort playlistRepositoryPort;

    @MockBean
    private MusicRepositoryPort musicRepositoryPort;

    static String invalidId = "1234";
    static String playlistId = "92d8123f-e9f6-4806-8e0e-1c6a5d46f2ed";
    static String musicId = "c96b8f6f-4049-4e6b-8687-82e29c05b735";
    static String playlistNotFoundMessage = "Playlist não encontrada na base de dados.";
    static String musicNotFoundMessage = "Música não encontrada na base de dados.";
    static String musicNotInPlaylistMessage = "Música não encontrada na listagem da playlist informada.";


    @DisplayName("Should save music on playlist properly")
    @Test
    void shouldSaveMusicOnPlaylistProperly() throws PlaylistNotFoundException, MusicNotFoundException {

        ArtistEntity artistEntity = getArtistEntity();
        MusicEntity musicEntity = getMusicEntity(artistEntity);
        UserEntity userEntity = getUserEntity();
        PlaylistEntity playlistEntity = getPlaylistEntity(userEntity);

        when(this.playlistRepositoryPort.findById(any())).thenReturn(Optional.of(playlistEntity));
        when(this.musicRepositoryPort.findById(any())).thenReturn(Optional.of(musicEntity));

        var response = service.saveMusicInPlaylist(playlistId, musicId);

        verify(playlistRepositoryPort, times(1)).savePlaylist(any(PlaylistEntity.class));

        assertEquals("Música adicionada à playlist com sucesso!", response);
    }

    @DisplayName("Should return playlist not found exception")
    @Test
    void shouldReturnPlaylistNotFoundException() {

        PlaylistNotFoundException playlistNotFoundException = assertThrows(PlaylistNotFoundException.class, () ->
                service.verifyIfPlaylistExists(invalidId));

        assertEquals(playlistNotFoundMessage, playlistNotFoundException.getMessage());
    }

    @DisplayName("Should return music not found exception")
    @Test
    void shouldReturnMusicNotFoundException() {

        MusicNotFoundException musicNotFoundException = assertThrows(MusicNotFoundException.class, () ->
                service.verifyIfMusicExists(invalidId));

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

        service.deleteMusicInPlaylist("1", "1");

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
                () -> service.verifyIfMusicExistsInPlaylist(playlistEntity, invalidMusicEntity));

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