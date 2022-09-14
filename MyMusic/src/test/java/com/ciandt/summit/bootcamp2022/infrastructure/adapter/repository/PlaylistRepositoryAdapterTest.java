package com.ciandt.summit.bootcamp2022.infrastructure.adapter.repository;

import com.ciandt.summit.bootcamp2022.domain.data.entity.ArtistEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.UserEntity;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {PlaylistRepositoryAdapter.class})
@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
class PlaylistRepositoryAdapterTest {

    @Autowired
    private PlaylistRepositoryAdapter playlistRepositoryAdapter;

    @MockBean
    private SpringPlaylistRepository springPlaylistRepository;

    @Test
    @DisplayName("Should save playlist entity properly")
    void shouldSavePlaylist() {

        PlaylistEntity playlistEntity = getPlaylistEntity();

        when(springPlaylistRepository.save(playlistEntity)).thenReturn(playlistEntity);

        playlistRepositoryAdapter.savePlaylist(playlistEntity);

        verify(springPlaylistRepository, times(1)).save(any(PlaylistEntity.class));
    }

    @Test
    @DisplayName("Should return playlist entity properly")
    void shouldFindPlaylistById() {

        PlaylistEntity playlistEntity = getPlaylistEntity();

        when(springPlaylistRepository.findById(any())).thenReturn(Optional.of(playlistEntity));

        var responsePlaylistEntity = playlistRepositoryAdapter.findById("1");

        assertNotNull(responsePlaylistEntity);

        assertEquals("1", responsePlaylistEntity.get().getId());
    }

    private static PlaylistEntity getPlaylistEntity() {
        ArtistEntity artistEntity = getArtistEntity();
        MusicEntity musicEntity = getMusicEntity(artistEntity);
        UserEntity userEntity = getUserEntity();
        PlaylistEntity playlistEntity = getPlaylistEntity(userEntity);
        playlistEntity.getMusicEntityList().add(musicEntity);
        return playlistEntity;
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

    private static MusicEntity getMusicEntity(ArtistEntity artistEntity) {
        MusicEntity musicEntity = new MusicEntity();
        musicEntity.setArtistEntity(artistEntity);
        musicEntity.setId("1");
        musicEntity.setName("Matheus");
        return musicEntity;
    }

    private static ArtistEntity getArtistEntity() {
        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setId("1");
        artistEntity.setName("Eric");
        return artistEntity;
    }
}