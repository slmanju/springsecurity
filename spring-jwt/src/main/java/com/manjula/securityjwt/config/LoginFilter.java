package com.manjula.securityjwt.config;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter for the login requests
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    public LoginFilter(AuthenticationManager authManager) {
        setAuthenticationManager(authManager);
    }

    /**
     * After Spring finds the user by username and verifies that the passwords match,
     * it calls the successfulAuthentication() method to store the user as logged in.
     * We override that method (from UsernamePasswordAuthenticationFilter)
     * in order to provide a token instead of a Session object.
     */
    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {
        res.addHeader(HttpHeaders.AUTHORIZATION, JwtUtils.authorization(auth.getName()));
        res.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization");
    }

}
