package com.ciandt.summit.bootcamp2022.infrastructure.adapter.repository;

import com.ciandt.summit.bootcamp2022.domain.data.entity.UserEntity;
import com.ciandt.summit.bootcamp2022.domain.port.repository.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final SpringUserRepository springUserRepository;
    @Override
    public Optional<UserEntity> findById(String idUser) {
        return springUserRepository.findById(idUser);
    }

    @Override
    public void save(UserEntity userEntity) {
        springUserRepository.save(userEntity);
    }
}
