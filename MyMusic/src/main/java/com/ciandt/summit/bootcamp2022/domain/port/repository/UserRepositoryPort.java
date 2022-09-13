package com.ciandt.summit.bootcamp2022.domain.port.repository;

import com.ciandt.summit.bootcamp2022.domain.data.entity.UserEntity;

import java.util.Optional;

public interface UserRepositoryPort {

    String findUserTypeById(String userId);

    Optional<UserEntity> findById(String userId);

    void saveUser(UserEntity userEntity);
}