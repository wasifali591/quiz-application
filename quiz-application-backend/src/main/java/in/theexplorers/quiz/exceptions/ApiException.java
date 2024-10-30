package in.theexplorers.quiz.exceptions;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * This is a custom exception class.
 * It extends {@link RuntimeException}.
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public class ApiException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus;
}
