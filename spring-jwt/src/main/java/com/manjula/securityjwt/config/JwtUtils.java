package com.manjula.securityjwt.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public final class JwtUtils {

    public static final long EXPIRATION_TIME = 864_000_00; // 1 day in milliseconds
    public static final String SECRET_KEY = "these violent delights have violent ends";
    public static final String PREFIX = "Bearer";

    public static String authorization(String name) {
        return PREFIX + " " + generate(name);
    }

    public static String generate(String name) {
        return Jwts.builder().setSubject(name)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public static String parse(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token.replace(PREFIX, ""))
                .getBody()
                .getSubject();
    }

}
