package com.ciandt.summit.bootcamp2022.domain.service;

import com.ciandt.summit.bootcamp2022.domain.data.entity.TipoUsuarioEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.UserEntity;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.UserServicePort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.UserRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;

    public Optional<UserEntity> verifyIfUserExists(String userId) throws UserNotFoundException {
        return Optional.ofNullable(userRepositoryPort.findById(userId).orElseThrow(UserNotFoundException::new));
    }

    public String verifyUserType(String userId) {
        return userRepositoryPort.findUserTypeById(userId);
    }

    public boolean userIsPremium(String userId) throws UserNotFoundException {

        UserEntity userEntity = verifyIfUserExists(userId).get();

        String userType = verifyUserType(userEntity.getId());

        if (userType.equals("sa764b91-1235-2s9x-2k4e-2s5687x4lco2")) {
            return true;
        }

        return false;
    }

    @Override
    public String updateUserType(String userId, String userTypeId) {

        UserEntity userEntity = userRepositoryPort.findById(userId).get();

        TipoUsuarioEntity tipoUsuarioEntity = new TipoUsuarioEntity();
        tipoUsuarioEntity.setId(userTypeId);
        userEntity.setTipoUsuarioEntity(tipoUsuarioEntity);

        userRepositoryPort.saveUser(userEntity);

        return "Usuário atualizado com sucesso!";
    }

}
