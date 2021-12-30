package me.luraframework.auth.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.common.collect.ImmutableMap;
import me.luraframework.auth.exception.InvalidTokenException;
import me.luraframework.auth.security.business.BusinessJwtUser;
import me.luraframework.core.commons.JsonUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class TokenProvider {

    private static final String USER_CLAIM_KEY = "USER";
    private static final String USER_TYPE_CLAIM_KEY = "TYPE";


    public String createToken(JwtUser user) {
        return JWT.create()
                  .withClaim(USER_TYPE_CLAIM_KEY, user.getUserType().name())
                  .withClaim(USER_CLAIM_KEY, JsonUtils.toStr(user))
                  .sign(Algorithm.HMAC256(user.getUsername()));

    }

    public JwtUser checkToken(HttpServletRequest request) {
        String token = request.getHeader("Token");
        try {
            String userType = JWT.decode(token).getClaim(USER_TYPE_CLAIM_KEY).asString();
            String userStr = JWT.decode(token).getClaim(USER_CLAIM_KEY).asString();
            JwtUser jwtUser;
            if (JwtUser.UserType.valueOf(userType) == JwtUser.UserType.CUSTOMER) {
                jwtUser = JsonUtils.toObj(userStr, JwtUser.class);
            }else if(JwtUser.UserType.valueOf(userType) == JwtUser.UserType.BUSINESS){
                jwtUser = JsonUtils.toObj(userStr, BusinessJwtUser.class);
            }else {
                throw new InvalidTokenException(ImmutableMap.of("token", token));
            }
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(jwtUser.getUsername())).build();
            jwtVerifier.verify(token);
            return jwtUser;
        } catch (Exception e) {
            throw new InvalidTokenException(ImmutableMap.of("token", token));
        }
    }

    public String getToken(HttpServletRequest request) {
        return request.getHeader("Token");
    }


}
