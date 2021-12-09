package me.luraframework.core.commons.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
public class AppException extends RuntimeException {

    private final ErrorCode errorCode;
    private final Map<String, Object> data = new HashMap<>();

    public AppException(ErrorCode errorCode, Map<String, Object> data) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        if (!CollectionUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }
}
