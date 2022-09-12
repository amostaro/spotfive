package com.ciandt.summit.bootcamp2022.application.controller;

import com.ciandt.summit.bootcamp2022.domain.port.interfaces.UserServicePort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.UserBadRequestException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserServicePort userServicePort;

//    @Operation(description = "")
//    @PutMapping("/{userId}")
//    public ResponseEntity<String> updateUserType(@RequestParam @PathVariable String userId) throws UserBadRequestException {
//
//        String updateUser = userServicePort.metodo(userId);
//
//        return new ResponseEntity<>(updateUser, HttpStatus.OK);
//    }

    @Operation(description = "")
    @GetMapping("/{userId}")
    public ResponseEntity<String> getUserType(@RequestParam @PathVariable String userId) throws UserBadRequestException {
        var getUser = userServicePort.verifyUserType(userId);
        return new ResponseEntity<>(getUser, HttpStatus.OK);
    }
}
