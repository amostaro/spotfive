package com.ciandt.summit.bootcamp2022.domain.port.interfaces;

import com.ciandt.summit.bootcamp2022.domain.data.entity.UserEntity;
import com.ciandt.summit.bootcamp2022.domain.service.exception.UserNotFoundException;

public interface UserServicePort {

    UserEntity findUserById(String idUser) throws UserNotFoundException;

    void changeUserProfile(String userId) throws UserNotFoundException;
}
