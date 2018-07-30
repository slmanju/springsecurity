package com.manjula.securityjwt.config;

import com.manjula.securityjwt.view.UserView;
import org.springframework.security.core.userdetails.User;

public class UserPrincipal extends User {

    private String id;
    private String fullname;
    private String[] roles;

    // User(String username, String password, Collection<? extends GrantedAuthority> authorities)
    public UserPrincipal(UserView userView) {
        super(userView.getUsername(), userView.getPassword(), userView.authorities());
        this.id = userView.getId();
        this.fullname = userView.getFirstName() + " " + userView.getLastName();
        this.roles = userView.getRoles();
    }

    public String getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String[] getRoles() {
        return roles;
    }

}
