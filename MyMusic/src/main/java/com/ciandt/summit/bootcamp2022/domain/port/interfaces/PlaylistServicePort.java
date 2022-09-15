package com.ciandt.summit.bootcamp2022.domain.port.interfaces;

import com.ciandt.summit.bootcamp2022.domain.service.exception.*;

public interface PlaylistServicePort {

    String saveMusicInPlaylist(String idPlaylist, String idMusic, String userId) throws PlaylistNotFoundException, MusicNotFoundException, MusicNotInPlaylistException, UserNotFoundException, MusicLimitException;

    void deleteMusicInPlaylist(String idPlaylist, String idMusic) throws PlaylistNotFoundException, MusicNotFoundException, MusicNotInPlaylistException;
}
