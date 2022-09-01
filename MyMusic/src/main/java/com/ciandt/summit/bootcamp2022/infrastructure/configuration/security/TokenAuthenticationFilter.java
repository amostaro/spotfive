package com.ciandt.summit.bootcamp2022.infrastructure.configuration.security;

import com.ciandt.summit.bootcamp2022.infrastructure.configuration.security.dto.CreateAuthorizerRequest;
import com.ciandt.summit.bootcamp2022.infrastructure.configuration.security.dto.CreateAuthorizerRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private ValidationToken validationToken;

    protected static final String BEARER = "Bearer ";

    public TokenAuthenticationFilter(ValidationToken validationToken) {
        this.validationToken = validationToken;
    }

    public void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain filterChain) throws IOException {

        String token = getTokenFromHeader(request);

        CreateAuthorizerRequestData createAuthorizerRequestData = new CreateAuthorizerRequestData();
        createAuthorizerRequestData.setToken(token);

        CreateAuthorizerRequest data = new CreateAuthorizerRequest();
        data.setData(createAuthorizerRequestData);

        validationToken.validationTokenMethod(request, response, filterChain, data);
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return null;
        }
        return token.replace(BEARER, "");
    }


}
