package me.luraframework.auth.security;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("login")
    public Object login(@RequestBody AuthUserDto authUserDto) {
        return authService.login(authUserDto);
    }

    @PostMapping("check")
    public JwtUser check(HttpServletRequest request) {
        return authService.check(request);
    }

    @PostMapping("logout")
    public void logout(HttpServletRequest request) {
        authService.logout(request);
    }

    @PostMapping("register")
    public void register(@RequestBody AuthUserDto authUserDto) {
        authService.register(authUserDto);
    }
}

