package me.luraframework.core.commons.exception;

public interface ErrorCode {
    int getStatus();

    String getCode();

    String getMessage();

}
