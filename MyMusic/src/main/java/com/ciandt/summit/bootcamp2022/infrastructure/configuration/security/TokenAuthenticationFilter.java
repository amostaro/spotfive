package com.ciandt.summit.bootcamp2022.infrastructure.configuration.security;

import com.ciandt.summit.bootcamp2022.infrastructure.configuration.feign.AuthenticationApi;
import com.ciandt.summit.bootcamp2022.infrastructure.configuration.security.dto.CreateAuthorizerRequest;
import com.ciandt.summit.bootcamp2022.infrastructure.configuration.security.dto.CreateAuthorizerRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationApi authenticationApi;

    protected static final String BEARER = "Bearer ";

    public TokenAuthenticationFilter(AuthenticationApi authenticationApi) {
        this.authenticationApi = authenticationApi;
    }

    public void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain filterChain) throws IOException {

        String token = getTokenFromHeader(request);
        String nomeUsuario = "teste";

        CreateAuthorizerRequestData createAuthorizerRequestData = new CreateAuthorizerRequestData();
        createAuthorizerRequestData.setName(nomeUsuario);
        createAuthorizerRequestData.setToken(token);

        CreateAuthorizerRequest data = new CreateAuthorizerRequest();
        data.setData(createAuthorizerRequestData);

        try {
            authenticationApi.isValidToken(data);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(nomeUsuario, null, null);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            (response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
        }
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return null;
        }
        return token.replace(BEARER, "");
    }


}
