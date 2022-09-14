package com.ciandt.summit.bootcamp2022.domain.port.interfaces;

import com.ciandt.summit.bootcamp2022.domain.data.entity.UserEntity;

import java.util.Optional;

public interface UserServicePort {

    Optional<UserEntity> verifyIfUserExists(String userId);

    String verifyUserType (String userId);

    boolean userIsPremium(String userId);

    String updateUserType(String userId, String userTypeId);
}
