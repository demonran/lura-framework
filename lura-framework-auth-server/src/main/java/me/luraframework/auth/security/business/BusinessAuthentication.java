package me.luraframework.auth.security.business;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class BusinessAuthentication extends UsernamePasswordAuthenticationToken {
    public BusinessAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
