package me.luraframework.gateway.exception;

import me.luraframework.commons.exception.AppException;
import me.luraframework.commons.exception.ErrorCode;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

@Configuration
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(request, options);

        if (getError(request) instanceof AppException) {
            AppException ex = (AppException) getError(request);
            ErrorCode errorCode = ex.getErrorCode();
            map.put("message", errorCode.getMessage());
            map.put("status", errorCode.getStatus());
            map.put("code", errorCode.getCode());
            map.put("data", ex.getData());

            return map;
        }

        map.put("exception", "SystemException");
        map.put("message", "System Error , Check logs!");
        map.put("status", "500");
        map.put("error", " System Error ");
        return map;
    }
}
