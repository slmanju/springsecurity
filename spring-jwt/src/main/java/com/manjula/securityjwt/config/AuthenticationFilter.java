package com.manjula.securityjwt.config;

import com.manjula.securityjwt.view.UserView;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Filter for other requests to check JWT in header
 * responsible for taking the token and re-identify the logged user from it
 */
public class AuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String token = ((HttpServletRequest) request).getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null && token.startsWith(JwtUtils.TOKEN_PREFIX)) {
            try {
                Claims claims = JwtUtils.parse(token);
                List<SimpleGrantedAuthority> roles = JwtUtils.getRoles(claims);
                List<String> authorities = roles.stream().map(SimpleGrantedAuthority::getAuthority).collect(Collectors.toList());

                // Should load from database/ or token
                UserView userView = UserView.builder()
                        .id(JwtUtils.getId(claims))
                        .username(JwtUtils.getUsername(claims))
                        .password("password")
                        .firstName("Manjula")
                        .lastName("Jayawardana")
                        .roles(authorities.toArray(new String[authorities.size()])).build();

                Authentication authentication = new UsernamePasswordAuthenticationToken(new UserPrincipal(userView), null, roles);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                // Do something
            }
        }
        filterChain.doFilter(request, response);
    }

}
