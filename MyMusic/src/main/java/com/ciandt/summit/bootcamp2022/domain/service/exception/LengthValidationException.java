package com.ciandt.summit.bootcamp2022.domain.service.exception;

public class LengthValidationException extends Exception {
    private static final String MESSAGE = "Operação inválida com os parâmetros buscados. A pesquisa precisa conter no mínimo 2 caracteres.";
    public LengthValidationException() {
        super(MESSAGE);
    }
}
