package com.ciandt.summit.bootcamp2022.domain.port.interfaces;

import com.ciandt.summit.bootcamp2022.domain.service.exception.MusicNotFoundException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.MusicNotInPlaylistException;
import com.ciandt.summit.bootcamp2022.domain.service.exception.PlaylistNotFoundException;

public interface PlaylistServicePort {

    String saveMusicInPlaylist(String idPlaylist, String idMusic) throws PlaylistNotFoundException, MusicNotFoundException;

    void deleteMusicInPlaylist(String idPlaylist, String idMusic) throws PlaylistNotFoundException, MusicNotFoundException, MusicNotInPlaylistException;
}
