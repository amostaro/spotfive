package com.ciandt.summit.bootcamp2022.application.controller;

import com.ciandt.summit.bootcamp2022.domain.data.entity.ArtistEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.UserEntity;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {PlaylistController.class})
@WebMvcTest(controllers = PlaylistController.class)
class PlaylistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlaylistController playlistController;

    @MockBean
    private PlaylistServiceImpl playlistService;

    static String playlistId = "92d8123f-e9f6-4806-8e0e-1c6a5d46f2ed";
    static String musicId = "c96b8f6f-4049-4e6b-8687-82e29c05b735";

    @DisplayName("Should add music in Playlist properly")
    @Test
    void shouldAddMusicInPlaylistProperly() throws Exception {

        when(playlistService.saveMusicInPlaylist(playlistId, musicId)).thenReturn("Música adicionada à playlist com sucesso!");

        var response = playlistController.addMusicInPlaylist(musicId, playlistId);

        assertNotNull(response);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @DisplayName("Should remove music from Playlist properly")
    @Test
    void shouldRemoveMusicFromPlaylistProperly() throws MusicNotInPlaylistException, MusicNotFoundException, PlaylistNotFoundException {

        ArtistEntity artistEntity = getArtistEntity();
        MusicEntity musicEntity = getMusicEntity(artistEntity);
        UserEntity userEntity = getUserEntity();
        PlaylistEntity playlistEntity = getPlaylistEntity(userEntity);
        playlistEntity.getMusicEntityList().add(musicEntity);

        playlistService.verifyIfMusicExistsInPlaylist(playlistEntity, musicEntity);

        var responseEntity = playlistController.removeMusicInPlaylist(musicId, playlistId);

        verify(playlistService, times(1)).deleteMusicInPlaylist(playlistId,musicId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

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