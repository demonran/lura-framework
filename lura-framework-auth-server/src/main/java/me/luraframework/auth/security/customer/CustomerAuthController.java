package me.luraframework.auth.security.customer;

import lombok.RequiredArgsConstructor;
import me.luraframework.auth.security.AuthUserDto;
import me.luraframework.auth.security.JwtUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
public class CustomerAuthController {

    private final CustomerAuthService authService;


    @PostMapping("login")
    public Object login(@RequestBody AuthUserDto authUserDto) {
        return authService.login(authUserDto);
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

