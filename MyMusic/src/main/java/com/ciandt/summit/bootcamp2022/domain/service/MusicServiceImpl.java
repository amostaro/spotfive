package com.ciandt.summit.bootcamp2022.domain.service;

import com.ciandt.summit.bootcamp2022.domain.data.dto.DataDTO;
import com.ciandt.summit.bootcamp2022.domain.data.dto.MusicDTO;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.MusicServicePort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.MusicRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.ArtistOrMusicNotFoundException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.LengthValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class MusicServiceImpl implements MusicServicePort {

    private final MusicRepositoryPort musicRepositoryPort;


    @Override
    public DataDTO findAllByNameLikeIgnoreCase(String searchName) throws LengthValidationException,
            ArtistOrMusicNotFoundException {
        log.info("Iniciando busca de artistas ou músicas de acordo com os parâmetros '"+searchName+"', em: " + Calendar.getInstance().getTime() + ".");

        if (searchName.length() < 3) {
            log.info("Log de Operação inválida com os parâmetros '"+searchName+"'. A busca precisa ter no mínimo 3 caracteres, em: " + Calendar.getInstance().getTime() + ".");
            throw new LengthValidationException("Operação inválida com os parâmetros '"+searchName+"'. A busca precisa ter no mínimo 3 caracteres.");
        }
        List<MusicDTO> artistEntityAndMusicEntityListOrderByName =
                this.musicRepositoryPort.findArtistEntityAndMusicEntityListOrderByName(searchName);


        if (artistEntityAndMusicEntityListOrderByName.isEmpty()) {
            log.info("Log de resultado de pesquisa: sua pesquisa com os parâmetros '"+searchName+"' não retornou nenhum artista ou música, em: " + Calendar.getInstance().getTime() + ".");
            throw new ArtistOrMusicNotFoundException("Sua pesquisa com os parâmetros '"+searchName+"' não retornou nenhum artista ou música.");
        }

        DataDTO dataDTO = new DataDTO();
        dataDTO.setData(new HashSet<>(artistEntityAndMusicEntityListOrderByName));
        Set<MusicDTO> collect = dataDTO.getData().stream()
                .sorted(Comparator.comparing(MusicDTO::getName))
                .sorted(Comparator.comparing(musicDTO -> musicDTO.getArtistEntity().getName()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        dataDTO.setData(collect);
        log.info("Busca com os parâmetros '"+searchName+"' finalizada com sucesso, em: " + Calendar.getInstance().getTime() + ".");
        return dataDTO;
    }
}
