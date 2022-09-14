package com.ciandt.summit.bootcamp2022.domain.service;

import com.ciandt.summit.bootcamp2022.domain.data.dto.ArtistDTO;
import com.ciandt.summit.bootcamp2022.domain.data.dto.DataDTO;
import com.ciandt.summit.bootcamp2022.domain.data.dto.MusicDTO;
import com.ciandt.summit.bootcamp2022.domain.data.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.domain.port.repository.MusicRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.ArtistOrMusicNotFoundException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.LengthValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {MusicServiceImpl.class})
@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
class MusicServiceImplTest {

    @Autowired
    private MusicServiceImpl musicService;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private MusicRepositoryPort musicRepositoryPort;

    static String lengthValidationTest = "E";
    static String lengthValidationMessage = "Operação inválida com os parâmetros buscados. A pesquisa precisa conter no mínimo 2 caracteres.";
    static String musicOrArtistNotFoundTest = "Teste de Musica";
    static String musicOrArtistNotFoundMessage = "Sua pesquisa com os parâmetros buscados não retornou nenhum artista ou música.";

    @Test
    @DisplayName("Should return music list properly")
    void shouldReturnMusicListProperlyTest() throws ArtistOrMusicNotFoundException, LengthValidationException {

        List<MusicEntity> musicEntityList = new ArrayList<>();
        List<MusicDTO> musicDTOList = new ArrayList<>();

        MusicEntity musicEntity = getMusicEntity();
        MusicDTO musicDTO = getMusicDTO();
        getArtistDTO(musicDTO);

        DataDTO dataDTO = getDataDTO();
        dataDTO.setData(Set.of(musicDTO));

        musicEntityList.add(musicEntity);
        musicDTOList.add(musicDTO);
        String searchName = "Eric";

        when(modelMapper.map(musicEntity, MusicDTO.class)).thenReturn(musicDTO);
        when(this.musicRepositoryPort.findArtistEntityAndMusicEntityListOrderByName(searchName)).thenReturn(musicEntityList);

        DataDTO methodReturnDTO = musicService.findAllByNameLikeIgnoreCase(searchName);

        assertEquals(dataDTO, methodReturnDTO);
    }

    @Test
    @DisplayName("Should return length validation exception")
    void shouldReturnLengthValidationExceptionTest() throws LengthValidationException {

        LengthValidationException lengthValidationException = assertThrows(LengthValidationException.class, () ->
                musicService.findAllByNameLikeIgnoreCase(lengthValidationTest));

        assertEquals(lengthValidationMessage, lengthValidationException.getMessage());
    }

    @Test
    @DisplayName("Should return artist or music not found exception")
    void shouldReturnArtistOrMusicNotFoundExceptionTest() throws ArtistOrMusicNotFoundException {

        ArtistOrMusicNotFoundException artistOrMusicNotFoundException =
                assertThrows(ArtistOrMusicNotFoundException.class,
                        () -> musicService.findAllByNameLikeIgnoreCase(musicOrArtistNotFoundTest));

        assertEquals(musicOrArtistNotFoundMessage, artistOrMusicNotFoundException.getMessage());
    }

    private static void getArtistDTO(MusicDTO musicDTO) {
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setId("44bee025-4006-4093-8d5d-f330764d9dd0");
        artistDTO.setName("Eric Clapton");
        musicDTO.setArtistEntity(artistDTO);
    }

    private static MusicEntity getMusicEntity() {
        MusicEntity musicDTO = new MusicEntity();
        musicDTO.setId("349110e6-4124-49e7-b4c0-d8cbda1bf935");
        musicDTO.setName("When You Got A Good Friend");
        return musicDTO;
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
