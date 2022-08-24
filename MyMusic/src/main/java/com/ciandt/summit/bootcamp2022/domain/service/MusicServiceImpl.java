package com.ciandt.summit.bootcamp2022.domain.service;

import com.ciandt.summit.bootcamp2022.domain.data.dto.MusicDTO;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.MusicServicePort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.MusicRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.ArtistOrMusicNotFoundException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.LengthValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class MusicServiceImpl implements MusicServicePort {

    private final MusicRepositoryPort musicRepositoryPort;


    @Override
    public List<MusicDTO> findAllByNameLikeIgnoreCase(String searchName) throws LengthValidationException, ArtistOrMusicNotFoundException {

        log.info("Iniciando busca de artistas ou músicas de acordo com os parâmetros, em: " + Calendar.getInstance().getTime()+".");

        if (searchName.length() < 3) {
            log.info("Log de Operação inválida. A busca precisa ter no mínimo 3 caracteres, em: " + Calendar.getInstance().getTime()+".");
            throw new LengthValidationException("Operação inválida. A busca precisa ter no mínimo 3 caracteres.");
        }
        List<MusicDTO> artistEntityAndMusicEntityListOrderByName = this.musicRepositoryPort.findArtistEntityAndMusicEntityListOrderByName(searchName);

        if (artistEntityAndMusicEntityListOrderByName.isEmpty()) {
            log.info("Log de resultado de pesquisa: sua pesquisa não retornou nenhum artista ou música, em: " + Calendar.getInstance().getTime()+".");
            throw new ArtistOrMusicNotFoundException("Sua pesquisa não retornou nenhum artista ou música.");
        }

        log.info("Busca finalizada com sucesso, em: "+Calendar.getInstance().getTime()+".");
        return artistEntityAndMusicEntityListOrderByName;
    }
}
