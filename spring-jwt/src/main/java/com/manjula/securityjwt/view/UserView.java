package com.manjula.securityjwt.view;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data @Builder
public class UserView {

    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String[] roles;

    public List<SimpleGrantedAuthority> authorities() {
        return Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

}
