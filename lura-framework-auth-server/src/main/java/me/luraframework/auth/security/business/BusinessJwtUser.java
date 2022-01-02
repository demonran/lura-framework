package me.luraframework.auth.security.business;

import lombok.NoArgsConstructor;
import me.luraframework.auth.security.JwtUser;
import org.springframework.security.core.GrantedAuthority;

import java.util.stream.Collectors;

@NoArgsConstructor
public class BusinessJwtUser extends JwtUser {
    public BusinessJwtUser(Business business) {
        this.setId(business.getId());
        this.setUserType(UserType.BUSINESS);
        this.setUsername(business.getUsername());
        this.setRoles(business.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
    }
}
