package com.ciandt.token.provider.core.usecases;

import com.ciandt.token.provider.exceptions.RequestNotAuthorizedException;
import com.ciandt.token.provider.services.EncryptServices;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreateAuthorizerUseCase {

    private EncryptServices encryptServices;

    public CreateAuthorizerUseCase(EncryptServices encryptServices) {
        this.encryptServices = encryptServices;
    }

    public String execute(final String token) throws RequestNotAuthorizedException {
        if(token == null){
            throw new RequestNotAuthorizedException("Token vazio");
        }
        final String session = encryptServices.decrypt(token);
        if (isExpiredToken(session)) {
            throw new SecurityException("Token expirado");
        }
        return "ok";
    }

    private boolean isExpiredToken(final String token) {
        final LocalDateTime dateTime = LocalDateTime.parse(token);
        final LocalDateTime dateLimit = dateTime.plusMinutes(240000);
        return dateLimit.compareTo(LocalDateTime.now()) < 0;
    }
}
