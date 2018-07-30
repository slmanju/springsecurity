package com.manjula.securityjwt.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUtils {

    public static final long EXPIRATION_TIME = 864_000_00; // 1 day in milliseconds
    public static final String SECRET_KEY = "these violent delights have violent ends";
    public static final String TOKEN_PREFIX = "Bearer";

    public static String authorization(Authentication authentication) {
        return TOKEN_PREFIX + " " + generate(authentication);
    }

    public static String generate(Authentication authentication) {
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("id", user.getId());
        claims.put("roles", user.getRoles());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public static Claims parse(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody();
    }

    public static String getId(Claims claims) {
        return (String) claims.get("id");
    }

    public static String getUsername(Claims claims) {
        return claims.getSubject();
    }

    public static List<SimpleGrantedAuthority> getRoles(Claims claims) {
        List<String> roles = (ArrayList<String>) claims.get("roles");
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

}
