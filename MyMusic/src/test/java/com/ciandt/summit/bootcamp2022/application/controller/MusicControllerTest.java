package com.ciandt.summit.bootcamp2022.application.controller;

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

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        var responseEntityDataDTO = musicController.getArtistOrMusic(musicOrArtistExistTest);

        assertEquals(HttpStatus.OK, responseEntityDataDTO.getStatusCode());
    }

}
