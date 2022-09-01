package com.ciandt.summit.bootcamp2022.domain.service.exception;

public class LengthValidationException extends Throwable {
    private static String message = "Operação inválida com os parâmetros buscados. A pesquisa precisa conter no mínimo 2 caracteres.";
    public LengthValidationException() {
        super(message);
    }
}
