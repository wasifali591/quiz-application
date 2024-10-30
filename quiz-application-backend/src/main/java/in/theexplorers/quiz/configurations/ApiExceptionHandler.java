package in.theexplorers.quiz.configurations;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.dtos.response.ApiResponseDto;
import in.theexplorers.quiz.exceptions.ApiException;
import in.theexplorers.quiz.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This is a configuration class for Exception Handler.
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * This method handle any exceptions to the type {@link ApiException}
     *
     * @param exception - custom Exception to be thrown
     * @return response
     */
    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<Object> handleApiException(ApiException exception) {
        return new ApiResponseDto().generateResponse(exception.getHttpStatus(), null, exception.getMessage());
    }

    /**
     * This method handle any exceptions to the type {@link ValidationException}
     *
     * @param exception - custom Exception to be thrown
     * @return response
     */
    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<Object> handleValidationException(ValidationException exception) {
        return new ApiResponseDto().generateResponse(HttpStatus.BAD_REQUEST, null, exception.getMessage());
    }
}
