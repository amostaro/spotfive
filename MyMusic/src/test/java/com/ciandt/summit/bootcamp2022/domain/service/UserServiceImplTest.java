package com.ciandt.summit.bootcamp2022.domain.service;

import com.ciandt.summit.bootcamp2022.domain.data.entity.UserEntity;
import com.ciandt.summit.bootcamp2022.domain.port.repository.UserRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserRepositoryPort userRepositoryPort;

    static String userId = "dd444a81-9588-4e6b-9d3d-1f1036a6eaa1";
    static String invalidUserId = "dd444a81-9588-4e6b-9d3d-1f1036a6eaa1z";

    @DisplayName("Should find a user by id properly")
    @Test
    void shouldFindUserByIdProperly() throws UserNotFoundException {

        UserEntity userEntity = getUserEntity();

        when(this.userRepositoryPort.findById(any())).thenReturn(Optional.of(userEntity));

        var response = userService.findUserById(userId);

        assertEquals(userEntity, response);
    }

    @DisplayName("Should return user not found exception")
    @Test
    void shouldReturnUserNotFoundException() throws UserNotFoundException {

        var userNotFoundException = assertThrows(UserNotFoundException.class, () ->
                userService.findUserById(invalidUserId));

        assertEquals("Usuário não encontrado na base de dados.", userNotFoundException.getMessage());
    }

    @DisplayName("Should change a user profile by id properly")
    @Test
    void shouldChangeUserProfileProperly() throws UserNotFoundException {

        UserEntity userEntity = getUserEntity();

        when(this.userRepositoryPort.findById(any())).thenReturn(Optional.of(userEntity));

        userService.changeUserProfile("1");

        verify(userRepositoryPort, times(1)).save(any(UserEntity.class));

    }

    private static UserEntity getUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("1");
        userEntity.setName("André");
        userEntity.setPlaylistId("1");
        userEntity.setTipoUsuarioId("1");
        return userEntity;
    }
}