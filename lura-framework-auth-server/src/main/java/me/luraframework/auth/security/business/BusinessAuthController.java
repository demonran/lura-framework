package me.luraframework.auth.security.business;

import lombok.RequiredArgsConstructor;
import me.luraframework.auth.security.AuthUserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("business")
@RequiredArgsConstructor
public class BusinessAuthController {

    private final BusinessAuthService businessAuthService;

    @PostMapping("login")
    public String  login(@RequestBody AuthUserDto authUserDto) {
        return businessAuthService.login(authUserDto);
    }

    @PostMapping("register")
    public void  register(@RequestBody AuthUserDto authUserDto) {
        businessAuthService.register(authUserDto);
    }

}
