package io.luraframework.security.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class JwtUser {
    private Long id;
    private String username;
    private Set<String> roles;
    private UserType userType;


    public enum UserType {
        CUSTOMER, BUSINESS
    }
}
