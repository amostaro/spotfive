package com.ciandt.summit.bootcamp2022.infrastructure.adapter.repository;

import com.ciandt.summit.bootcamp2022.domain.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringUserRepository extends JpaRepository<UserEntity, String> {
    @Query("SELECT TipoUsuarioId from Usuarios WHERE Id = userId")
    String findTypeById(String userId);
    Optional<UserEntity> findById(String userId);

    Optional<UserEntity> save(Optional<UserEntity> userEntity);
}

