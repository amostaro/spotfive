package com.ciandt.summit.bootcamp2022.infrastructure.adapter.repository;

import com.ciandt.summit.bootcamp2022.domain.data.entity.UserEntity;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserRepositoryAdapter.class})
@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
class UserRepositoryAdapterTest {

    @Autowired
    private UserRepositoryAdapter userRepositoryAdapter;

    @MockBean
    private SpringUserRepository springUserRepository;

    static String userId = "dd444a81-9588-4e6b-9d3d-1f1036a6eaa1";

    @DisplayName("Should find user type by user id")
    @Test
    void shouldFindUserTypeById() {

        UserEntity userEntity = getUserEntity();

        when(this.springUserRepository.findTypeById(any())).thenReturn(userEntity.getTipoUsuarioId());

        var response = userRepositoryAdapter.findUserTypeById(userId);

        assertEquals(userEntity.getTipoUsuarioId(), response);
    }

    @DisplayName("Should find user by user id")
    @Test
    void shouldFindById() {

        UserEntity userEntity = getUserEntity();

        when(this.springUserRepository.findById(any())).thenReturn(Optional.of(userEntity));

        var response = userRepositoryAdapter.findById(userId);

        assertEquals(userEntity, response.get());
    }

    @DisplayName("Should save user by user entity")
    @Test
    void saveUser() {

        UserEntity userEntity = getUserEntity();

        userRepositoryAdapter.saveUser(userEntity);

        verify(springUserRepository, times(1)).save(any(UserEntity.class));
    }

    private static UserEntity getUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("1");
        userEntity.setName("André");
        return userEntity;
    }
}