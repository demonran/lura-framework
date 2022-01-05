package me.luraframework.auth.exception;

import me.luraframework.commons.exception.AppException;

import java.util.Map;

public class InvalidTokenException extends AppException {
    public InvalidTokenException(Map<String, Object> data) {
        super(AuthErrorCode.INVALID_TOKEN, data);
    }
}
