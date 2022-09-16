package com.ciandt.summit.bootcamp2022.application.controller;

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

    @Operation(description = "Altera o tipo de usuario")
    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUserType(@PathVariable String userId, @RequestParam String userTypeId ) throws UserNotFoundException {

        String updateUser = userServicePort.updateUserType(userId, userTypeId);

        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @Operation(description = "Retorna o Id do tipo de usuario")
    @GetMapping("/{userId}")
    public ResponseEntity<String> getUserType(@RequestParam @PathVariable String userId) {
        var getUser = userServicePort.verifyUserType(userId);
        return new ResponseEntity<>(getUser, HttpStatus.OK);
    }
}
