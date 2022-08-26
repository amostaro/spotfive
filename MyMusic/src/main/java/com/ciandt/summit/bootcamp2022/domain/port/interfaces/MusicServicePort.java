package com.ciandt.summit.bootcamp2022.domain.port.interfaces;

import com.ciandt.summit.bootcamp2022.domain.data.dto.DataDTO;
import com.ciandt.summit.bootcamp2022.domain.service.exception.ArtistOrMusicNotFoundException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.LengthValidationException;
import org.springframework.cache.annotation.Cacheable;

public interface MusicServicePort {


    @Cacheable(value = "DataDTO")
    DataDTO findAllByNameLikeIgnoreCase(String seachName) throws LengthValidationException, ArtistOrMusicNotFoundException;
}
