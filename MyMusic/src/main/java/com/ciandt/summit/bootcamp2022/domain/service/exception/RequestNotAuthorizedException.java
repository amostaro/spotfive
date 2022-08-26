package com.ciandt.summit.bootcamp2022.domain.service.exception;

import java.io.IOException;

public class RequestNotAuthorizedException extends IOException {
    public RequestNotAuthorizedException(String message) {
        super(message);
    }
}
