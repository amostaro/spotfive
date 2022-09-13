package com.ciandt.summit.bootcamp2022.domain.service;

import com.ciandt.summit.bootcamp2022.domain.data.entity.TipoUsuarioEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.UserEntity;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.UserServicePort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.UserRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.Objects;


@RequiredArgsConstructor
public class UserServiceImpl implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public UserEntity findUserById(String idUser) throws UserNotFoundException {
        return userRepositoryPort.findById(idUser)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void changeUserProfile(String userId) throws UserNotFoundException {
        UserEntity userEntity = this.findUserById(userId);
        TipoUsuarioEntity tipoUsuarioEntity = new TipoUsuarioEntity();
        if (Objects.equals(userEntity.getTipoUsuarioId(), "mi561c28-4956-4k9c-3s4e-6l5461v3uio8")) {
            tipoUsuarioEntity.setId("sa764b91-1235-2s9x-2k4e-2s5687x4lco2");
            tipoUsuarioEntity.setDescricao("Premium");
        } else {
            tipoUsuarioEntity.setId("mi561c28-4956-4k9c-3s4e-6l5461v3uio8");
            tipoUsuarioEntity.setDescricao("Comum");
        }
        userEntity.setTipoUsuarioEntity(tipoUsuarioEntity);
        userRepositoryPort.save(userEntity);
    }
}
