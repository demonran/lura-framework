package me.luraframework.core.commons.exception;

import lombok.Getter;
import lombok.Value;

import java.time.Instant;
import java.util.Map;

@Getter
@Value(staticConstructor = "of")
public class ErrorDetail {

    String code;
    String message;
    String path;
    Instant timestamp;
    Map<String, Object> data;
}
