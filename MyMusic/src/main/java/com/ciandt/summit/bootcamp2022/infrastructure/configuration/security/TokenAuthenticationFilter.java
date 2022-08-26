package com.ciandt.summit.bootcamp2022.infrastructure.configuration.security;

import antlr.StringUtils;
import com.ciandt.summit.bootcamp2022.domain.service.exception.RequestNotAuthorizedException;
import com.ciandt.summit.bootcamp2022.infrastructure.configuration.feign.AuthenticationApi;
import com.ciandt.summit.bootcamp2022.infrastructure.configuration.security.dto.CreateAuthorizerRequest;
import com.ciandt.summit.bootcamp2022.infrastructure.configuration.security.dto.CreateAuthorizerRequestData;
import com.hazelcast.internal.util.StringUtil;
import lombok.SneakyThrows;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
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
                                 FilterChain filterChain) throws ServletException, IOException {
        // recuperar token da request...
        String token = getTokenFromHeader(request);

        CreateAuthorizerRequestData createAuthorizerRequestData = new CreateAuthorizerRequestData();
        String nomeUsuario = "teste";
        createAuthorizerRequestData.setName(nomeUsuario);
        createAuthorizerRequestData.setToken(token);
        CreateAuthorizerRequest data = new CreateAuthorizerRequest();
        data.setData(createAuthorizerRequestData);
        // validar token
        try {
            authenticationApi.isValidToken(data);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(nomeUsuario, null, null);
            // setando autenticação
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            filterChain.doFilter(request, response);
        }catch (Exception e){
            SecurityContextHolder.getContext().setAuthentication(null);
        }

    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return null;
        }
        return token.replace(BEARER, ""); // "Bearer "21414124 -> 21414124
    }


}
