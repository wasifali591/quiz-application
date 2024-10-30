package in.theexplorers.quiz.exceptions;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import org.springframework.http.HttpStatus;

/**
 * This is a custom exception class for resource not found.
 * It extends {@link ApiException}
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
public class ResourceNotFoundException extends ApiException {

    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
