package io.luraframework.security.handler;

import io.luraframework.security.annotation.UserContext;
import io.luraframework.security.model.BusinessJwtUser;
import io.luraframework.security.model.CustomerJwtUser;
import me.luraframework.commons.utils.JsonUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class JwtUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserContext.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String userInfo = webRequest.getHeader("userInfo");
        if (parameter.getParameterType() == BusinessJwtUser.class) {
            return JsonUtils.toObj(userInfo, BusinessJwtUser.class);
        } else if (parameter.getParameterType() == CustomerJwtUser.class) {
            return JsonUtils.toObj(userInfo, CustomerJwtUser.class);
        }
        return null;
    }
}
