package me.luraframework.auth.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.luraframework.auth.security.customer.Customer;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class JwtUser {
    private String username;
    private Set<String> roles;
    private UserType userType;


    public enum UserType {
        CUSTOMER, BUSINESS
    }
}
