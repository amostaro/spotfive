package com.ciandt.summit.bootcamp2022.infrastructure.configuration.security;

import com.ciandt.summit.bootcamp2022.infrastructure.configuration.feign.AuthenticationApiIntegration;
import com.ciandt.summit.bootcamp2022.infrastructure.configuration.security.dto.CreateAuthorizerRequest;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class ValidationToken {

    @Autowired
    private AuthenticationApiIntegration authenticationApiIntegration;

    void validationTokenMethod(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, CreateAuthorizerRequest data) throws IOException {
        try {
            authenticationApiIntegration.isValidToken(data);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken("Nome vindo do Banco", null, null);

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            filterChain.doFilter(request, response);
        } catch (FeignException.Unauthorized e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
        } catch (ServletException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
