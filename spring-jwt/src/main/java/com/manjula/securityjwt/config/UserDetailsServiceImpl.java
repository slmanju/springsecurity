package com.manjula.securityjwt.config;

import com.manjula.securityjwt.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals("manjula")) {
            throw new UsernameNotFoundException("not found");
        }
        String[] roles = { "ADMIN", "MANAGER" };
        UserView userView = UserView.builder()
                .id(System.currentTimeMillis() + "")
                .firstName("Manjula")
                .lastName("Jayawardana")
                .username("manjula")
                .password(passwordEncoder.encode("password"))
                .roles(roles).build();
        return new UserPrincipal(userView);
    }

}
