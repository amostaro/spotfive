package com.ciandt.summit.bootcamp2022.application.controller;

import com.ciandt.summit.bootcamp2022.domain.data.entity.UserEntity;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.UserServicePort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
class UserControllerTest {

    @Autowired
    private UserController userController;

    @MockBean
    private UserServicePort userServicePort;

    static String userId = "dd444a81-9588-4e6b-9d3d-1f1036a6eaa1";
    static String userTypeId = "mi561c28-4956-4k9c-3s4e-6l5461v3uio8";

    @DisplayName("Should return response entity ok for update user type by user id")
    @Test
    void shouldUpdateUserTypeProperly() throws UserNotFoundException {

        UserEntity userEntity = getUserEntity();

        when(this.userServicePort.updateUserType(any(),any())).thenReturn(userEntity.getTipoUsuarioId());

        var stringResponseEntity = userController.updateUserType(userId, userTypeId);

        assertEquals(HttpStatus.OK, stringResponseEntity.getStatusCode());

    }

    @DisplayName("Should return response entity ok for get user type by user id")
    @Test
    void shouldGetUserTypeProperly() {

        UserEntity userEntity = getUserEntity();

        when(this.userServicePort.verifyUserType(any())).thenReturn(userEntity.getTipoUsuarioId());

        var response = userController.getUserType(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    private static UserEntity getUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("1");
        userEntity.setName("André");
        return userEntity;
    }
}
