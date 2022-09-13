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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    @DisplayName("Should return user entity properly")
    @Test
    void shouldFindById() {

        UserEntity userEntity = getUserEntity();

        when(springUserRepository.findById(any())).thenReturn(Optional.of(userEntity));

        var responseOptionalUserEntity = userRepositoryAdapter.findById("1");

        assertNotNull(responseOptionalUserEntity);

        assertEquals("1", responseOptionalUserEntity.get().getId());
    }

    @Test
    @DisplayName("Should save user entity properly")
    void shouldSaveUser() {

        UserEntity userEntity = getUserEntity();

        when(springUserRepository.save(userEntity)).thenReturn(userEntity);

        userRepositoryAdapter.save(userEntity);

        verify(springUserRepository, times(1)).save(any(UserEntity.class));
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