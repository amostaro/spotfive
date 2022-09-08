package com.ciandt.summit.bootcamp2022.application.controller;

import com.ciandt.summit.bootcamp2022.domain.data.entity.ArtistEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.UserEntity;
import com.ciandt.summit.bootcamp2022.domain.port.repository.MusicRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.PlaylistRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.MusicServiceImpl;
import com.ciandt.summit.bootcamp2022.domain.service.PlaylistServiceImpl;
import com.ciandt.summit.bootcamp2022.domain.service.exception.MusicNotFoundException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.MusicNotInPlaylistException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.PlaylistNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {PlaylistController.class, PlaylistRepositoryPort.class, MusicRepositoryPort.class})
@WebMvcTest(controllers = PlaylistController.class)
class PlaylistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlaylistController playlistController;

    @MockBean
    private PlaylistServiceImpl playlistService;

    @MockBean
    private MusicServiceImpl musicService;

    @MockBean
    private PlaylistRepositoryPort playlistRepositoryPort;

    @MockBean
    private MusicRepositoryPort musicRepositoryPort;

    @DisplayName("Should add music in Playlist properly")
    @Test
    void shouldAddMusicInPlaylistProperly() throws Exception {
        String playlistId = "92d8123f-e9f6-4806-8e0e-1c6a5d46f2ed";
        String musicId = "c96b8f6f-4049-4e6b-8687-82e29c05b735";

        when(playlistService.saveMusicInPlaylist(playlistId, musicId)).thenReturn("Música adicionada à playlist com " +
                "sucesso!");

        ResponseEntity<String> response = playlistController.addMusicInPlaylist(musicId, playlistId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @DisplayName("Should remove music from Playlist properly")
    @Test
    void shouldRemoveMusicFromPlaylistProperly() throws MusicNotInPlaylistException, MusicNotFoundException,
            PlaylistNotFoundException {
        ArtistEntity artistEntity = getArtistEntity();
        MusicEntity musicEntity = getMusicEntity(artistEntity);
        UserEntity userEntity = getUserEntity();
        PlaylistEntity playlistEntity = getPlaylistEntity(userEntity);
        playlistEntity.getMusicEntityList().add(musicEntity);

        String playlistId = "92d8123f-e9f6-4806-8e0e-1c6a5d46f2ed";
        String musicId = "c96b8f6f-4049-4e6b-8687-82e29c05b735";

        playlistService.verifyIfMusicExistsInPlaylist(playlistEntity, musicEntity);
        playlistService.deleteMusicInPlaylist(playlistId, musicId);

        ResponseEntity<String> response = playlistController.removeMusicInPlaylist(musicId, playlistId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

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