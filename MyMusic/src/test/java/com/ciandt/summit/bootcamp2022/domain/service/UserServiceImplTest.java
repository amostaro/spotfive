package com.ciandt.summit.bootcamp2022.domain.service;

import com.ciandt.summit.bootcamp2022.domain.data.entity.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.TipoUsuarioEntity;
import com.ciandt.summit.bootcamp2022.domain.data.entity.UserEntity;
import com.ciandt.summit.bootcamp2022.domain.port.repository.UserRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.UserNotFoundException;
import com.ciandt.summit.bootcamp2022.infrastructure.adapter.repository.SpringUserRepository;
import com.ciandt.summit.bootcamp2022.infrastructure.adapter.repository.UserRepositoryAdapter;

import java.util.HashSet;

import org.hsqldb.rights.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserRepositoryPort userRepositoryPort;

    static String userId = "1";
    static String userTypeId = "mi561c28-4956-4k9c-3s4e-6l5461v3uio8";
    static String userBadRequestExceptionMessage = "Usuário não encontrado.";

    @DisplayName("Should verify if user exist by user id")
    @Test
    void shouldVerifyIfUserExistsProperly() throws UserNotFoundException {

        UserEntity userEntity = getUserEntity();

        when(this.userRepositoryPort.findById(any())).thenReturn(Optional.of(userEntity));

        var response = userService.verifyIfUserExists(userId);

        assertEquals(userEntity, response);

    }

    @DisplayName("Should return User Bad Request Exception")
    @Test
    void shouldReturnUserBadRequestException() {

        UserNotFoundException userNotFoundException = assertThrows(UserNotFoundException.class, () ->
                userService.verifyIfUserExists(userId));

        assertEquals(userBadRequestExceptionMessage, userNotFoundException.getMessage());

    }

    @DisplayName("Should verify user type by user id")
    @Test
    void shouldVerifyUserTypeProperly() {

        UserEntity userEntity = getUserEntity();

        when(this.userRepositoryPort.findUserTypeById(any())).thenReturn(userEntity.getTipoUsuarioId());

        String response = userService.verifyUserType(userId);

        assertEquals(userEntity.getTipoUsuarioId(), response);
    }

    @DisplayName("Should verify if user is premium by user id")
    @Test
    void shouldVerifyIfUserIsPremiumProperly() throws UserNotFoundException {

        TipoUsuarioEntity tipoUsuarioEntity = new TipoUsuarioEntity();
        tipoUsuarioEntity.setId("sa764b91-1235-2s9x-2k4e-2s5687x4lco2");

        UserEntity userEntity = getUserEntity();
        userEntity.setTipoUsuarioEntity(tipoUsuarioEntity);

        when(this.userRepositoryPort.findById(any())).thenReturn(Optional.of(userEntity));
        when(this.userRepositoryPort.findUserTypeById(any())).thenReturn((userEntity.getTipoUsuarioEntity().getId()));

        boolean responseBoolean = userService.userIsPremium(userId);

        assertTrue(responseBoolean);

    }

    @DisplayName("Should verify if user is premium by user id and return false")
    @Test
    void shouldVerifyIfUserIsPremiumAndReturnFalseProperly() throws UserNotFoundException {

        TipoUsuarioEntity tipoUsuarioEntity = new TipoUsuarioEntity();
        tipoUsuarioEntity.setId("sa764b91-1235-2s9x-2k4e-2s5687x4lco2");

        UserEntity userEntity = getUserEntity();
        userEntity.setTipoUsuarioEntity(tipoUsuarioEntity);

        when(this.userRepositoryPort.findById(any())).thenReturn(Optional.of(userEntity));
        when(this.userRepositoryPort.findUserTypeById(any())).thenReturn(("1"));

        boolean responseBoolean = userService.userIsPremium(userId);

        assertFalse(responseBoolean);

    }

    @DisplayName("Should update user type by user id")
    @Test
    void shouldUpdateUserTypeProperly() throws UserNotFoundException {
        UserEntity userEntity = getUserEntity();
        when(this.userRepositoryPort.findById(any())).thenReturn(Optional.of(userEntity));
        var response = userService.updateUserType(userId, userTypeId);

        verify(userRepositoryPort, times(1)).saveUser(any(UserEntity.class));

        assertEquals("Usuário atualizado com sucesso!", response);
    }

    @DisplayName("Should update user type by user id")
    @Test
    void shouldUpdateUserTypeProperlyVerify() throws UserNotFoundException {
        UserEntity userEntity = mock(UserEntity.class);

        when(this.userRepositoryPort.findById(any())).thenReturn(Optional.of(userEntity));

        userService.updateUserType(userId, userTypeId);

        verify(userEntity, times(1))
                .setTipoUsuarioEntity(any(TipoUsuarioEntity.class));
    }
    @DisplayName("Should Alter Type user entity ")
    @Test
    void shouldAlterTypeUserEntity(){
        TipoUsuarioEntity tipoUsuarioEntity1 = userService.getTipoUsuarioEntity("3");

        assertEquals("3", tipoUsuarioEntity1.getId());
    }

    private static UserEntity getUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("1");
        userEntity.setName("André");
        return userEntity;
    }
}
