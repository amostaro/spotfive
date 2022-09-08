package com.ciandt.summit.bootcamp2022.domain.service;

import com.ciandt.summit.bootcamp2022.domain.data.dto.DataDTO;
import com.ciandt.summit.bootcamp2022.domain.data.dto.MusicDTO;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.MusicServicePort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.MusicRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.ArtistOrMusicNotFoundException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.LengthValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class MusicServiceImpl implements MusicServicePort {

    private final MusicRepositoryPort musicRepositoryPort;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public DataDTO findAllByNameLikeIgnoreCase(String searchName) throws LengthValidationException,
            ArtistOrMusicNotFoundException {
        log.info("Iniciando busca de artistas ou músicas de acordo com os parâmetros '" + searchName + "', em: " + Calendar.getInstance().getTime() + ".");

        if (searchName.length() <= 1) {
            log.error("Log de Operação inválida com os parâmetros '" + searchName + "'. A busca precisa ter no mínimo" +
                    " 2 caracteres, em: " + Calendar.getInstance().getTime() + ".");
            throw new LengthValidationException();
        }

        List<MusicDTO> artistEntityAndMusicEntityListOrderByName =
                this.musicRepositoryPort.findArtistEntityAndMusicEntityListOrderByName(searchName).stream()
                        .map(music -> modelMapper.map(music, MusicDTO.class)).collect(Collectors.toList());


        if (artistEntityAndMusicEntityListOrderByName.isEmpty()) {
            log.error("Log de resultado de pesquisa: sua pesquisa com os parâmetros '" + searchName + "' não retornou" +
                    " nenhum artista ou música, em: " + Calendar.getInstance().getTime() + ".");
            throw new ArtistOrMusicNotFoundException();
        }

        DataDTO dataDTO = new DataDTO();
        dataDTO.setData(new LinkedHashSet<>(artistEntityAndMusicEntityListOrderByName));

        log.info("Busca com os parâmetros '" + searchName + "' finalizada com sucesso, em: " + Calendar.getInstance().getTime() + ".");
        return dataDTO;
    }
}
