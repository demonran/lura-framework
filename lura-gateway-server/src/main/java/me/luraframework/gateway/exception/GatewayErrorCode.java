package me.luraframework.gateway.exception;

import me.luraframework.commons.exception.ErrorCode;

public enum GatewayErrorCode implements ErrorCode {
    INVALID_TOKEN(403, "无效Token"),
    UNAUTHORIZED(401, "未认证");

    private final int status;
    private final String message;

    GatewayErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
