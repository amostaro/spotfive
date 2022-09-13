package com.ciandt.summit.bootcamp2022.application.controller;

import com.ciandt.summit.bootcamp2022.domain.data.entity.UserEntity;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.UserServicePort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ApiResponses(
        value = {
                @ApiResponse(responseCode = "200", description = "Requisição feita com sucesso"),
                @ApiResponse(responseCode = "401", description = "Você não tem permissão para acessar este recurso"),
                @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
                @ApiResponse(responseCode = "204", description = "Não foi encontrado nenhum dado")
        }
)

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServicePort userServicePort;

    @Operation(description = "Realiza a busca de um usuário pelo seu id e retorna os dados do usuário.")
    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable String userId) throws UserNotFoundException {

        UserEntity userEntity = userServicePort.findUserById(userId);

        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }

    @PutMapping("/change-profile/{userId}")
    public void changeProfile(@PathVariable String userId) throws UserNotFoundException {
        userServicePort.changeUserProfile(userId);
    }
}
