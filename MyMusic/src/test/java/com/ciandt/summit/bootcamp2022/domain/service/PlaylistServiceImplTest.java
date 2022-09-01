package com.ciandt.summit.bootcamp2022.domain.service;

import com.ciandt.summit.bootcamp2022.domain.data.entity.ArtistEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.UserEntity;
import com.ciandt.summit.bootcamp2022.domain.port.repository.MusicRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.PlaylistRepositoryPort;
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


    @DisplayName("Should return playlist properly")
    @Test
    public void shouldSaveMusicOnPlaylistProperly() throws PlaylistNotFoundException, MusicNotFoundException {
        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setId("1");
        artistEntity.setName("Eric");

        MusicEntity musicEntity = new MusicEntity();
        musicEntity.setArtistEntity(artistEntity);
        musicEntity.setId("1");
        musicEntity.setName("Matheus");

        UserEntity userEntity = new UserEntity();
        userEntity.setId("1");
        userEntity.setName("André");

        PlaylistEntity playlistEntity = new PlaylistEntity();
        playlistEntity.setMusicEntityList(new HashSet<>());
        playlistEntity.setId("1");
        playlistEntity.setUserEntityList(Set.of(userEntity));

        String playlistId = "92d8123f-e9f6-4806-8e0e-1c6a5d46f2ed";
        String musicId = "c96b8f6f-4049-4e6b-8687-82e29c05b735";

        when(this.playlistRepositoryPort.findById(any())).thenReturn(Optional.of(playlistEntity));
        when(this.musicRepositoryPort.findById(any())).thenReturn(Optional.of(musicEntity));

        var response = service.saveMusicInPlaylist(playlistId, musicId);

        Assert.assertEquals("Música adicionada à playlist com sucesso!", response);
    }


}