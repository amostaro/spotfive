package com.ciandt.summit.bootcamp2022.domain.port.interfaces;

import com.ciandt.summit.bootcamp2022.domain.data.dto.ArtistDTO;

import java.util.List;

public interface ArtistServicePort {

    List<ArtistDTO> findByArtistOrMusic();
}
