package com.ciandt.summit.bootcamp2022.domain.service;

import com.ciandt.summit.bootcamp2022.domain.data.dto.ArtistDTO;
import com.ciandt.summit.bootcamp2022.domain.data.dto.DataDTO;
import com.ciandt.summit.bootcamp2022.domain.data.dto.MusicDTO;
import com.ciandt.summit.bootcamp2022.domain.port.repository.MusicRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.ArtistOrMusicNotFoundException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.LengthValidationException;
import org.junit.Assert;
import org.junit.Before;
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
import java.util.Set;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {MusicServiceImpl.class})
@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
class MusicServiceImplTest {

    @Autowired
    private MusicServiceImpl musicService;
    @MockBean
    private MusicRepositoryPort musicRepositoryPort;

    @Before
    public void init() {

    }

    @Test
    public void shouldReturnMusicListProperlyTest() throws ArtistOrMusicNotFoundException, LengthValidationException {
        List<MusicDTO> musicDTOList = new ArrayList<>();

        MusicDTO musicDTO = getMusicDTO();
        getArtistDTO(musicDTO);

        DataDTO dataDTO = getDataDTO();
        dataDTO.setData(Set.of(musicDTO));

        musicDTOList.add(musicDTO);

        String searchName = "Eric";

        when(this.musicRepositoryPort.findArtistEntityAndMusicEntityListOrderByName(searchName))
                .thenReturn(musicDTOList);


        DataDTO methodReturnDTO = musicService.findAllByNameLikeIgnoreCase(searchName);

        Assert.assertEquals(dataDTO, methodReturnDTO);
    }

    @Test
    public void shouldReturnLengthValidationExceptionTest() throws LengthValidationException {

        LengthValidationException lengthValidationException = Assert.assertThrows(LengthValidationException.class, () ->
                musicService.findAllByNameLikeIgnoreCase("Er"));
        Assert.assertEquals("Operação inválida. A busca precisa ter no mínimo 3 caracteres.",
                lengthValidationException.getMessage());

    }

    @Test
    public void shouldReturnArtistOrMusicNotFoundExceptionTest() throws ArtistOrMusicNotFoundException {

        ArtistOrMusicNotFoundException artistOrMusicNotFoundException = Assert.assertThrows(ArtistOrMusicNotFoundException.class, () ->
                musicService.findAllByNameLikeIgnoreCase("Teste de Música"));
        Assert.assertEquals("Sua pesquisa não retornou nenhum artista ou música.",
                artistOrMusicNotFoundException.getMessage());

    }

    private static void getArtistDTO(MusicDTO musicDTO) {
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setId("44bee025-4006-4093-8d5d-f330764d9dd0");
        artistDTO.setName("Eric Clapton");
        musicDTO.setArtistEntity(artistDTO);
    }

    private static MusicDTO getMusicDTO() {
        MusicDTO musicDTO = new MusicDTO();
        musicDTO.setId("349110e6-4124-49e7-b4c0-d8cbda1bf935");
        musicDTO.setName("When You Got A Good Friend");
        return musicDTO;
    }

    private static DataDTO getDataDTO() {
        DataDTO dataDTO = new DataDTO();
        dataDTO.setData(Set.of(getMusicDTO()));
        return dataDTO;
    }
}