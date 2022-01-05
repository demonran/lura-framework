package me.luraframework.core.commons.exception;

import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.google.common.collect.ImmutableMap;
import me.luraframework.commons.exception.AppException;
import me.luraframework.commons.exception.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;
import static org.springframework.http.HttpStatus.TOO_MANY_REQUESTS;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorDetail> handleAppException(AppException ex, HttpServletRequest request) {
        ErrorCode errorCode = ex.getErrorCode();
        return ResponseEntity.status(errorCode.getStatus()).body(ErrorDetail.of(errorCode.getCode(), errorCode.getMessage(),
                request.getRequestURI(), Instant.now(), ex.getData()));
    }

    @ExceptionHandler(FlowException.class)
    public ResponseEntity<ErrorDetail> handleFlowException(FlowException e, HttpServletRequest request) {
        return ResponseEntity.status(TOO_MANY_REQUESTS.value())
                             .body(ErrorDetail.of(TOO_MANY_REQUESTS.name(), e.getMessage(),
                request.getRequestURI(), Instant.now(), ImmutableMap.of("rule", e.getRule())));
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorDetail> handleFlowException(Throwable e, HttpServletRequest request) {
        return ResponseEntity.status(SERVICE_UNAVAILABLE.value())
                             .body(ErrorDetail.of(SERVICE_UNAVAILABLE.name(), e.getMessage(),
                                     request.getRequestURI(), Instant.now(), ImmutableMap.of()));
    }
}
