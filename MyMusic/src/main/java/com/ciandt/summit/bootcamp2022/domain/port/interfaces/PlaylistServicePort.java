package com.ciandt.summit.bootcamp2022.domain.port.interfaces;

import com.ciandt.summit.bootcamp2022.domain.service.exception.MusicNotFoundException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.PlaylistNotFoundException;
import org.springframework.cache.annotation.Cacheable;

public interface PlaylistServicePort {

    @Cacheable(value = "musicPlaylist")
    String saveMusicInPlaylist(String idMusica, String idPlaylist) throws PlaylistNotFoundException, MusicNotFoundException;
}
