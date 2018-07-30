package com.manjula.securityjwt.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * Filter for the login requests
 */
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    public LoginFilter(AuthenticationManager authManager) {
        super(new AntPathRequestMatcher("/login", "POST"));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Authentication authentication;
        try {
            LoginView loginView = new ObjectMapper().readValue(request.getInputStream(), LoginView.class);
            authentication = getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginView.getUsername(),
                            loginView.getPassword(),
                            Collections.emptyList())
            );
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid data");
        }
        return authentication;
    }

    /**
     * After Spring finds the user by username and verifies that the passwords match,
     * it calls the successfulAuthentication() method to store the user as logged in.
     * We override that method in order to provide a token instead of a Session object.
     */
    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response, FilterChain chain,
            Authentication auth) throws IOException, ServletException {
        response.addHeader(HttpHeaders.AUTHORIZATION, JwtUtils.authorization(auth.getName()));
        response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization");
    }

}
