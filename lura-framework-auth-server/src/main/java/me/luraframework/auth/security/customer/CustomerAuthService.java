package me.luraframework.auth.security.customer;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import me.luraframework.auth.exception.InvalidTokenException;
import me.luraframework.auth.security.AuthUserDto;
import me.luraframework.auth.security.JwtUser;
import me.luraframework.auth.security.OnlineService;
import me.luraframework.auth.security.TokenProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class CustomerAuthService {


    private final ProviderManager providerManager;
    private final TokenProvider tokenProvider;
    private final OnlineService onlineService;
    private final CustomerRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public Map<String, Object> login(AuthUserDto authUserDto) {

      CustomerAuthentication authenticationToken
              = new CustomerAuthentication(authUserDto.getUsername(), authUserDto.getPassword());
      Authentication authentication = providerManager.authenticate(authenticationToken);
        Customer user = (Customer) authentication.getPrincipal();
        CustomerJwtUser jwtUser = new CustomerJwtUser(user);
        String token = tokenProvider.createToken(jwtUser);
        onlineService.addUser(token, user);
        return ImmutableMap.of("token", token, "user", jwtUser);
  }

    public void logout(HttpServletRequest request) {
        String token = tokenProvider.getToken(request);
        onlineService.removeUser(token);
    }


    public void register(AuthUserDto authUserDto) {
        userRepository.save(new Customer(authUserDto.getUsername(), passwordEncoder.encode(authUserDto.getPassword())));
    }
}
