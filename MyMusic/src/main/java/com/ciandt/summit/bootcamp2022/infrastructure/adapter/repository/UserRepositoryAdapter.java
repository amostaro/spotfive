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
    public String findUserTypeById(String userId) {
        return springUserRepository.findTypeById(userId);
    }

    @Override
    public Optional<UserEntity> findById(String userId) {
        return springUserRepository.findById(userId);
    }

    @Override
    public void saveUser(UserEntity userEntity) {
        springUserRepository.save(userEntity);
    }
}
