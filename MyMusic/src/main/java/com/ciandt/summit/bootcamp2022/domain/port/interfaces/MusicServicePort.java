package com.ciandt.summit.bootcamp2022.domain.port.interfaces;

import com.ciandt.summit.bootcamp2022.domain.data.dto.MusicDTO;
import com.ciandt.summit.bootcamp2022.domain.service.exception.ArtistOrMusicNotFoundException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.LengthValidationException;

import java.util.List;

public interface MusicServicePort {


    List<MusicDTO> findAllByNameLikeIgnoreCase(String seachName) throws LengthValidationException, ArtistOrMusicNotFoundException;
}
