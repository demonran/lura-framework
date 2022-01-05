package me.luraframework.auth.exception;

import me.luraframework.commons.exception.ErrorCode;

public enum AuthErrorCode implements ErrorCode {
    INVALID_TOKEN(403, "token无效");


    private final int status;
    private final String message;

    AuthErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public int getStatus() {
        return this.status;
    }

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
