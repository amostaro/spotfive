package com.ciandt.summit.bootcamp2022.infrastructure.configuration.feign;


import com.ciandt.summit.bootcamp2022.domain.service.exception.RequestNotAuthorizedException;
import com.ciandt.summit.bootcamp2022.infrastructure.configuration.security.dto.CreateAuthorizerRequest;
import feign.RequestLine;
import org.aspectj.lang.annotation.AfterThrowing;
import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name ="token-autentication", url="localhost:8081")
public interface AuthenticationApi {

    @PostMapping("/api/v1/token")
    String getToken();

    @PostMapping("/api/v1/token/authorizer")
    String isValidToken(CreateAuthorizerRequest createAuthorizerRequestData);


}
