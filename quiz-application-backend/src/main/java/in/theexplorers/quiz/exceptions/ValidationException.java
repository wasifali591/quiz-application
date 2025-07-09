package in.theexplorers.quiz.exceptions;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import org.springframework.http.HttpStatus;

/**
 * This is a custom exception class for validation.
 * It extends {@link ApiException}.
 * It validates user inputs
 *
 * @author Md Wasif Ali
 * @version 1.0.0
 * @since 1.0.0
 */
public class ValidationException extends ApiException {

    public ValidationException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
