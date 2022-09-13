package com.ciandt.summit.bootcamp2022.domain.port.repository;

import com.ciandt.summit.bootcamp2022.domain.data.entity.UserEntity;

import java.util.Optional;

public interface UserRepositoryPort {

    Optional<UserEntity> findById(String idUser);

    void save(UserEntity userEntity);
}