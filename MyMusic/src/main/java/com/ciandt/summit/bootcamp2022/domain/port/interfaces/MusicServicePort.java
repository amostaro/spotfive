package com.ciandt.summit.bootcamp2022.domain.port.interfaces;

import com.ciandt.summit.bootcamp2022.domain.data.dto.DataDTO;
import com.ciandt.summit.bootcamp2022.domain.service.exception.ArtistOrMusicNotFoundException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.LengthValidationException;

public interface MusicServicePort {


    DataDTO findAllByNameLikeIgnoreCase(String seachName) throws LengthValidationException, ArtistOrMusicNotFoundException;
}
