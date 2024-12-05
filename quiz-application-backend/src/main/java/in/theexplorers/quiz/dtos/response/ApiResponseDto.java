package in.theexplorers.quiz.dtos.response;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

/**
 * A structured API response DTO class for standardizing responses.
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto {

    private HttpStatus status;
    private Object payload;
    private String message;
    private LocalDateTime timestamp;

    /**
     * Generates a ResponseEntity containing the API response details.
     *
     * @param status  The HTTP status to be returned.
     * @param body    The response body to include in the response.
     * @param message A message providing additional information about the response.
     * @return A ResponseEntity object containing the response details.
     */
    @Operation(summary = "Generate a structured API response",
            description = "Generates a ResponseEntity for API responses, including status, payload, message, and timestamp.")
    public static ResponseEntity<ApiResponseDto> generateResponse(
            @Schema(description = "HTTP status code", example = "200") HttpStatus status,
            @Schema(description = "Response body", example = "{\"data\": \"example\"}") Object body,
            @Schema(description = "Additional message", example = "Request processed successfully") String message,
            @Schema(description = "Timestamp of the response", example = "yy-mm-dd hh-mm-ss") LocalDateTime timestamp) {

        ApiResponseDto response = new ApiResponseDto(status, body, message, timestamp);
        return new ResponseEntity<>(response, status);
    }
}

