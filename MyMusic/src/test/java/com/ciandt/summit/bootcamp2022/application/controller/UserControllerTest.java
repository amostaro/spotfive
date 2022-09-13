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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @DisplayName("Should return response entity ok for get user by id")
    @Test
    void shouldGetUserById() throws UserNotFoundException {

        UserEntity userEntity = getUserEntity();

        when(this.userServicePort.findUserById(any())).thenReturn(userEntity);

        var responseEntityUserEntity = userController.getUserById(userId);

        assertNotNull(responseEntityUserEntity.getBody());

        assertEquals(HttpStatus.OK, responseEntityUserEntity.getStatusCode());
        assertEquals("dd444a81-9588-4e6b-9d3d-1f1036a6eaa1", responseEntityUserEntity.getBody().getId());
        assertEquals("mariana", responseEntityUserEntity.getBody().getName());
        assertEquals("a39926f4-6acb-4497-884f-d4e5296ef652", responseEntityUserEntity.getBody().getPlaylistId());
        assertEquals("mi561c28-4956-4k9c-3s4e-6l5461v3uio8", responseEntityUserEntity.getBody().getTipoUsuarioId());
    }

    @DisplayName("Should change a user profile by id")
    @Test
    void shouldChangeProfile() throws UserNotFoundException {

        UserEntity userEntity = getUserEntity();

        userServicePort.changeUserProfile(userEntity.getId());

        userController.changeProfile(userId);

        verify(userServicePort, times(2)).changeUserProfile(userId);

    }

    private static UserEntity getUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("dd444a81-9588-4e6b-9d3d-1f1036a6eaa1");
        userEntity.setName("mariana");
        userEntity.setPlaylistId("a39926f4-6acb-4497-884f-d4e5296ef652");
        userEntity.setTipoUsuarioId("mi561c28-4956-4k9c-3s4e-6l5461v3uio8");
        return userEntity;
    }
}