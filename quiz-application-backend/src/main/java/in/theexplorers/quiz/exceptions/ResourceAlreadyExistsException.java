package in.theexplorers.quiz.exceptions;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import org.springframework.http.HttpStatus;

/**
 * This is a custom exception class for already exist resource.
 * It extends {@link ApiException}
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
public class ResourceAlreadyExistsException extends ApiException {

    public ResourceAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
