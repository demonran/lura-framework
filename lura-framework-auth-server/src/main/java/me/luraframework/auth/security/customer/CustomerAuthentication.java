package me.luraframework.auth.security.customer;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomerAuthentication extends UsernamePasswordAuthenticationToken {
    public CustomerAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
