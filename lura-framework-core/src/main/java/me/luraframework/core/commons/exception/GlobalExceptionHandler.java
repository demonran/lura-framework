package me.luraframework.core.commons.exception;

import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.google.common.collect.ImmutableMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

import static org.springframework.http.HttpStatus.TOO_MANY_REQUESTS;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(AppException.class)
    public ErrorDetail handleAppException(AppException ex, HttpServletRequest request) {
        ErrorCode errorCode = ex.getErrorCode();
        return ErrorDetail.of(errorCode.getCode(), errorCode.getStatus(), errorCode.getMessage(),
                request.getRequestURI(), Instant.now(), ex.getData());
    }

    @ExceptionHandler(FlowException.class)
    public ErrorDetail handleFlowException(FlowException e, HttpServletRequest request) {
        return ErrorDetail.of(TOO_MANY_REQUESTS.name(), TOO_MANY_REQUESTS.value(), e.getMessage(),
                request.getRequestURI(), Instant.now(), ImmutableMap.of("rule", e.getRule()));
    }
}
