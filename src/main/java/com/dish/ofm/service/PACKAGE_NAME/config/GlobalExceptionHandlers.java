package com.dish.ofm.service.PACKAGE_NAME.config;

import com.dish.ofm.service.APPLICATION_NAMEApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandlers {
    private final Logger LOGGER = LoggerFactory.getLogger(APPLICATION_NAMEApplication.class);

    @ExceptionHandler(value = RestClientException.class)
    public ResponseEntity<?>
    restClientErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logException(req, e);

        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?>
    defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    private void logException(HttpServletRequest req, Exception e) {
        String strBuilder = "Call to " +
            req.getRequestURL() +
            " with params" +
            req.getQueryString() +
            " failed";

        LOGGER.error(strBuilder, e);
    }
}
