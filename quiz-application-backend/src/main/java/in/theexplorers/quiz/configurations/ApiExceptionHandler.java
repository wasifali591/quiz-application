package in.theexplorers.quiz.configurations;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.dtos.response.ApiResponseDto;
import in.theexplorers.quiz.exceptions.ApiException;
import in.theexplorers.quiz.exceptions.ValidationException;
import in.theexplorers.quiz.utilities.DateTimeUtility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * This is a configuration class for Exception Handler.
 *
 * @author Md Wasif Ali
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
    protected ResponseEntity<ApiResponseDto> handleApiException(ApiException exception) {
        return ApiResponseDto.generateResponse(exception.getHttpStatus(), null, exception.getMessage(), LocalDateTime.parse(DateTimeUtility.getCurrentTimestamp()));
    }

    /**
     * This method handle any exceptions to the type {@link ValidationException}
     *
     * @param exception - custom Exception to be thrown
     * @return response
     */
    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<ApiResponseDto> handleValidationException(ValidationException exception) {
        return ApiResponseDto.generateResponse(HttpStatus.BAD_REQUEST, null, exception.getMessage(), LocalDateTime.parse(DateTimeUtility.getCurrentTimestamp()));
    }
}
