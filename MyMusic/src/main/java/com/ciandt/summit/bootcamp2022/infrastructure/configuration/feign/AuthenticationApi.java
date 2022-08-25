package com.ciandt.summit.bootcamp2022.infrastructure.configuration.feign;

import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value="token-autentication", url="localhost:8081/")
public interface AuthenticationApi {

    @RequestLine("Post /api/v1/token")
    String getToken();

    @RequestLine("Post /api/v1/token/authorizer")
    String isValidToken();


}
