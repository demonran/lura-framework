package me.luraframework.auth.security.business;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

public class BusinessAuthenticationProvider extends DaoAuthenticationProvider {

    public BusinessAuthenticationProvider(BusinessUserDetailService userDetailService) {
        setUserDetailsService(userDetailService);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return BusinessAuthentication.class.isAssignableFrom(authentication);
    }
}
