package com.ciandt.summit.bootcamp2022.domain.service;

import com.ciandt.summit.bootcamp2022.domain.data.dto.MusicDTO;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.MusicServicePort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.MusicRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.LengthValidationException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MusicServiceImpl implements MusicServicePort {

    private final MusicRepositoryPort musicRepositoryPort;


    @Override
    public List<MusicDTO> findAllByNameLikeIgnoreCase(String searchName) throws LengthValidationException {
        if(searchName.length() < 3) {
            throw new LengthValidationException("Operação inválida. A busca precisa ter no mínimo 3 caracteres.");
        }
            return this.musicRepositoryPort.findArtistEntityAndMusicEntityListOrderByName(searchName);

    }
}
