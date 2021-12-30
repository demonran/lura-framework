package me.luraframework.auth.config;

import me.luraframework.auth.security.customer.CustomerUserDetailsService;
import me.luraframework.auth.security.business.BusinessAuthenticationProvider;
import me.luraframework.auth.security.business.BusinessUserDetailService;
import me.luraframework.auth.security.customer.CustomerAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/**/login", "/**/register").permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public ProviderManager authenticationManagerBean(BusinessUserDetailService businessUserDetailService,
                                                     CustomerUserDetailsService customerUserDetailsService) throws Exception {
        return new ProviderManager(new BusinessAuthenticationProvider(businessUserDetailService), new CustomerAuthenticationProvider(customerUserDetailsService));
    }

}
