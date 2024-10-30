package in.theexplorers.quiz.exceptions;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import org.springframework.http.HttpStatus;

/**
 * This is a custom exception class for operation failed.
 * It extends {@link ApiException}
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
public class OperationFailedException extends ApiException {

    public OperationFailedException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
