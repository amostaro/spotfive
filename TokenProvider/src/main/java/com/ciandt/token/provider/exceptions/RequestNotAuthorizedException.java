package com.ciandt.token.provider.exceptions;

import java.io.IOException;

public class RequestNotAuthorizedException extends IOException {
    public RequestNotAuthorizedException(String message) {
        super(message);
    }
}
