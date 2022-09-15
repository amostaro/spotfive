package com.ciandt.summit.bootcamp2022.domain.port.interfaces;

import com.ciandt.summit.bootcamp2022.domain.data.entity.UserEntity;
import com.ciandt.summit.bootcamp2022.domain.service.exception.UserNotFoundException;

public interface UserServicePort {

    UserEntity verifyIfUserExists(String userId) throws UserNotFoundException;

    String verifyUserType (String userId);

    boolean userIsPremium(String userId) throws UserNotFoundException;

    String updateUserType(String userId, String userTypeId) throws UserNotFoundException;
}
