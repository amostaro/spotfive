package com.ciandt.summit.bootcamp2022.infrastructure.adapter.repository;

import com.ciandt.summit.bootcamp2022.domain.data.entity.MusicEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {MusicRepositoryAdapter.class})
@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
class MusicRepositoryAdapterTest {

    @Autowired
    private MusicRepositoryAdapter musicRepositoryAdapter;

    @MockBean
    private SpringMusicRepository springMusicRepository;

    static String searchMusicName = "When You Got A Good Friend";
    static String searchMusicId = "349110e6-4124-49e7-b4c0-d8cbda1bf935";

    @Test
    @DisplayName("Should return music list properly")
    void shouldFindArtistEntityAndMusicEntityListOrderByName() {

        List<MusicEntity> musicEntityList = new ArrayList<>();
        MusicEntity musicEntity = getMusicEntity();
        musicEntityList.add(musicEntity);

        when(springMusicRepository.findAllByNameLikeIgnoreCase(searchMusicName)).thenReturn(musicEntityList);

        var responseMusicEntityList = musicRepositoryAdapter.findArtistEntityAndMusicEntityListOrderByName(searchMusicName);

        assertNotNull(responseMusicEntityList);

        assertEquals(musicEntityList, responseMusicEntityList);
    }

    @Test
    @DisplayName("Should return music entity properly")
    void shouldFindMusicById() {

        MusicEntity musicEntity = getMusicEntity();

        when(springMusicRepository.findById(searchMusicId)).thenReturn(Optional.of(musicEntity));

        var responseMusicEntity = musicRepositoryAdapter.findById(searchMusicId);

        assertNotNull(responseMusicEntity);

        assertEquals(Optional.of(musicEntity), responseMusicEntity);
    }

    private static MusicEntity getMusicEntity() {
        MusicEntity musicDTO = new MusicEntity();
        musicDTO.setId("349110e6-4124-49e7-b4c0-d8cbda1bf935");
        musicDTO.setName("When You Got A Good Friend");
        return musicDTO;
    }
}