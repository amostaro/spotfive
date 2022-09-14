package com.ciandt.summit.bootcamp2022.domain.service.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserBadRequestException extends Exception {

    private static String MESSAGE = "Usuário não encontrado.";

    public UserBadRequestException () {
        super(MESSAGE);
    }
}
