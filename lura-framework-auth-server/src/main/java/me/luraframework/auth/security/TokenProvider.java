package me.luraframework.auth.security;

import com.google.common.collect.ImmutableMap;
import me.luraframework.auth.exception.InvalidTokenException;
import me.luraframework.core.commons.JsonUtils;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class TokenProvider {

    private static final String jwtKey = "securityKey";

    public String createToken(JwtUser user) {
        Jwt encode = JwtHelper.encode(JsonUtils.toStr(user), new MacSigner(jwtKey));
        return encode.getEncoded();
    }

    public JwtUser checkToken(HttpServletRequest request) {
         String token = request.getHeader("Token");
        try {
            Jwt jwt = JwtHelper.decode(token);
            jwt.verifySignature(new MacSigner(jwtKey));
            return JsonUtils.toObj(jwt.getClaims(), JwtUser.class);
        } catch (Exception e) {
            throw new InvalidTokenException(ImmutableMap.of("token", token));
        }
    }

    public String getToken(HttpServletRequest request) {
        return request.getHeader("Token");
    }
}
