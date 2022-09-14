package com.ciandt.summit.bootcamp2022.application.controller;

import com.ciandt.summit.bootcamp2022.domain.data.dto.ArtistDTO;
import com.ciandt.summit.bootcamp2022.domain.data.dto.DataDTO;
import com.ciandt.summit.bootcamp2022.domain.data.dto.MusicDTO;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.MusicServicePort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.ArtistOrMusicNotFoundException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.LengthValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = {MusicController.class})
@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
class MusicControllerTest {

    @Autowired
    private MusicController musicController;

    @MockBean
    private MusicServicePort musicServicePort;

    static String musicOrArtistExistTest = "nTw";

    @Test
    @DisplayName("Should return response entity ok for get artist or music")
    void shouldReturnResponseEntityOkForGetArtistOrMusic() throws LengthValidationException, ArtistOrMusicNotFoundException {

        MusicDTO musicDTO = getMusicDTO();
        getArtistDTO(musicDTO);

        DataDTO dataDTO = getDataDTO();
        dataDTO.setData(Set.of(musicDTO));

        when(musicServicePort.findAllByNameLikeIgnoreCase(musicOrArtistExistTest)).thenReturn(dataDTO);

        var responseEntityDataDTO = musicController.getArtistOrMusic(musicOrArtistExistTest);

        assertNotNull(responseEntityDataDTO.getBody());

        assertEquals(HttpStatus.OK, responseEntityDataDTO.getStatusCode());
        assertEquals(1, Objects.requireNonNull(responseEntityDataDTO.getBody()).getData().size());
        assertEquals("Eric Clapton", responseEntityDataDTO.getBody().getData().stream().findFirst().get().getArtistEntity().getName());
        assertEquals("44bee025-4006-4093-8d5d-f330764d9dd0", responseEntityDataDTO.getBody().getData().stream().findFirst().get().getArtistEntity().getId());
        assertEquals("When You Got A Good Friend", responseEntityDataDTO.getBody().getData().stream().findFirst().get().getName());
        assertEquals("349110e6-4124-49e7-b4c0-d8cbda1bf935", responseEntityDataDTO.getBody().getData().stream().findFirst().get().getId());
    }

    private static void getArtistDTO(MusicDTO musicDTO) {
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setId("44bee025-4006-4093-8d5d-f330764d9dd0");
        artistDTO.setName("Eric Clapton");
        musicDTO.setArtistEntity(artistDTO);
    }

    private static DataDTO getDataDTO() {
        DataDTO dataDTO = new DataDTO();
        dataDTO.setData(Set.of(getMusicDTO()));
        return dataDTO;
    }

    private static MusicDTO getMusicDTO() {
        MusicDTO musicDTO = new MusicDTO();
        musicDTO.setId("349110e6-4124-49e7-b4c0-d8cbda1bf935");
        musicDTO.setName("When You Got A Good Friend");
        return musicDTO;
    }

}
