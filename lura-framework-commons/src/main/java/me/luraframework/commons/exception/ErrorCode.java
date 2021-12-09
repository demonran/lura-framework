package me.luraframework.commons.exception;

public interface ErrorCode {
    int getStatus();

    String getCode();

    String getMessage();

}
