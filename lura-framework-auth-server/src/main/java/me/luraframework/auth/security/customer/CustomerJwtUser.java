package me.luraframework.auth.security.customer;

import lombok.NoArgsConstructor;
import me.luraframework.auth.security.JwtUser;
import me.luraframework.auth.security.business.Business;
import org.springframework.security.core.GrantedAuthority;

import java.util.stream.Collectors;

@NoArgsConstructor
public class CustomerJwtUser extends JwtUser {
    public CustomerJwtUser(Customer customer) {
        this.setId(customer.getId());
        this.setUserType(UserType.CUSTOMER);
        this.setUsername(customer.getUsername());
        this.setRoles(customer.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
    }
}
