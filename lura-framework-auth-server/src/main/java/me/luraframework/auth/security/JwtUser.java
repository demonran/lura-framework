package me.luraframework.auth.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class JwtUser {
    private String username;
    private Set<String> roles;

    public JwtUser(User user) {
        this.username = user.getUsername();
        this.roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
    }
}
