package me.luraframework.auth.security.customer;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

public class CustomerAuthenticationProvider extends DaoAuthenticationProvider {
    public CustomerAuthenticationProvider(CustomerUserDetailsService userDetailsService) {
        setUserDetailsService(userDetailsService);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomerAuthentication.class.isAssignableFrom(authentication);
    }
}
