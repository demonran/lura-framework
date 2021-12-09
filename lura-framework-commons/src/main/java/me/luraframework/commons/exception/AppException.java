package me.luraframework.commons.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper = false)
public class AppException extends RuntimeException {

    private final ErrorCode errorCode;
    private final Map<String, Object> data = new HashMap<>();

    public AppException(ErrorCode errorCode, Map<String, Object> data) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        if (Objects.nonNull(data) && !data.isEmpty()) {
            this.data.putAll(data);
        }
    }

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
