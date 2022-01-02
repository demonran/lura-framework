package me.luraframework.auth.security;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class AuthCheckController {

    private final TokenProvider tokenProvider;
    private final OnlineService onlineService;

    @PostMapping("check")
    public JwtUser check(HttpServletRequest request) {
        return tokenProvider.checkToken(request);
    }
}
