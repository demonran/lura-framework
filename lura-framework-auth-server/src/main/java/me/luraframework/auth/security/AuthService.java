package me.luraframework.auth.security;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import me.luraframework.auth.exception.InvalidTokenException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor

public class AuthService {


    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final OnlineService onlineService;


    public String login(AuthUserDto authUserDto) {

      UsernamePasswordAuthenticationToken authenticationToken
              = new UsernamePasswordAuthenticationToken(authUserDto.getUsername(), authUserDto.getPassword());
      Authentication authentication = authenticationManager.authenticate(authenticationToken);
        User user = (User) authentication.getPrincipal();
        JwtUser jwtUser = new JwtUser(user);
        String token = tokenProvider.createToken(jwtUser);
        onlineService.addUser(token, user);
        return token;
  }

    public void logout(HttpServletRequest request) {
        String token = tokenProvider.getToken(request);
        onlineService.removeUser(token);
    }

    public JwtUser check(HttpServletRequest request) {
        String token = tokenProvider.getToken(request);
        if (onlineService.getOne(token) != null) {
            return tokenProvider.checkToken(request);
        }
        throw new InvalidTokenException(ImmutableMap.of("token", token));

    }
}
