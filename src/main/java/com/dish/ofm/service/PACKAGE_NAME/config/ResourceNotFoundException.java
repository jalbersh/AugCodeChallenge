package com.dish.ofm.service.PACKAGE_NAME.config;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(ResourceNotFoundException.class);

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(UUID sessionId) {
        logger.warn("Session {} not found!", sessionId);
    }
}