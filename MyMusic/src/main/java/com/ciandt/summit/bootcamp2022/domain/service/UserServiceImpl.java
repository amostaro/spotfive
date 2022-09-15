package com.ciandt.summit.bootcamp2022.domain.service;

import com.ciandt.summit.bootcamp2022.domain.data.entity.TipoUsuarioEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.UserEntity;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.UserServicePort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.UserRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;

    public UserEntity verifyIfUserExists(String userId) throws UserNotFoundException {
        return userRepositoryPort.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public String verifyUserType(String userId) {
        return userRepositoryPort.findUserTypeById(userId);
    }

    public boolean userIsPremium(String userId) throws UserNotFoundException {

        UserEntity userEntity = verifyIfUserExists(userId);

        String userType = verifyUserType(userEntity.getId());

        return userType.equals("sa764b91-1235-2s9x-2k4e-2s5687x4lco2");
    }

    @Override
    public String updateUserType(String userId, String userTypeId) throws UserNotFoundException {

        UserEntity userEntity = verifyIfUserExists(userId);

        TipoUsuarioEntity tipoUsuarioEntity = new TipoUsuarioEntity();
        tipoUsuarioEntity.setId(userTypeId);
        userEntity.setTipoUsuarioEntity(tipoUsuarioEntity);

        userRepositoryPort.saveUser(userEntity);

        return "Usuário atualizado com sucesso!";
    }

}
