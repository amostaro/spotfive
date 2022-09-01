package com.ciandt.summit.bootcamp2022.domain.service;

import com.ciandt.summit.bootcamp2022.domain.data.entity.ArtistEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.UserEntity;
import com.ciandt.summit.bootcamp2022.domain.port.repository.MusicRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.PlaylistRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.LengthValidationException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.MusicNotFoundException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.PlaylistNotFoundException;
import org.junit.Assert;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

    String invalidId = "1234";


    @DisplayName("Should return playlist properly")
    @Test
    public void shouldSaveMusicOnPlaylistProperly() throws PlaylistNotFoundException, MusicNotFoundException {

        ArtistEntity artistEntity = getArtistEntity();
        MusicEntity musicEntity = getMusicEntity(artistEntity);
        UserEntity userEntity = getUserEntity();
        PlaylistEntity playlistEntity = getPlaylistEntity(userEntity);

        String playlistId = "92d8123f-e9f6-4806-8e0e-1c6a5d46f2ed";
        String musicId = "c96b8f6f-4049-4e6b-8687-82e29c05b735";

        when(this.playlistRepositoryPort.findById(any())).thenReturn(Optional.of(playlistEntity));
        when(this.musicRepositoryPort.findById(any())).thenReturn(Optional.of(musicEntity));

        var response = service.saveMusicInPlaylist(playlistId, musicId);

        Assert.assertEquals("Música adicionada à playlist com sucesso!", response);
    }
    @DisplayName("Should return playlist not found exception")
    @Test
    public void shouldReturnPlaylistNotFoundException (){

        PlaylistNotFoundException playlistNotFoundException = Assert.assertThrows(PlaylistNotFoundException.class, () ->
                service.verifyIfPlaylistExists(invalidId));

        String expectedMessage = "Playlist não encontrada na base de dados.";

        Assert.assertEquals(expectedMessage, playlistNotFoundException.getMessage());
    }
    @DisplayName("Should return music not found exception")
    @Test
    public void shouldReturnMusicNotFoundException (){

        MusicNotFoundException musicNotFoundException = Assert.assertThrows(MusicNotFoundException.class, () ->
                service.verifyIfMusicExists(invalidId));

        String expectedMessage = "Música não encontrada na base de dados.";

        Assert.assertEquals(expectedMessage, musicNotFoundException.getMessage());
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