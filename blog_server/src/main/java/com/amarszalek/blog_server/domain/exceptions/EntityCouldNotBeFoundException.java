package com.amarszalek.blog_server.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityCouldNotBeFoundException extends RuntimeException {
    public EntityCouldNotBeFoundException(String message) {
        super(message);
    }
}
