package com.ciandt.summit.bootcamp2022.domain.port.interfaces;

import com.ciandt.summit.bootcamp2022.domain.data.dto.MusicDTO;

import java.util.List;

public interface MusicServicePort {


    List<MusicDTO> findByArtistOrMusic(String seachName);
}
