package in.theexplorers.quiz.dtos.response;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * Standardized structure for API responses.
 * Includes status code, payload, message, and timestamp.
 *
 * @author Md Wasif Ali
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto {

    @Schema(description = "HTTP status code", example = "200")
    private int status;

    @Schema(description = "Response payload", example = "{\"data\": \"example\"}")
    private Object payload;

    @Schema(description = "Message explaining the result of the operation", example = "Request processed successfully")
    private String message;

    @Schema(description = "Timestamp of the response", example = "2025-07-13 18:22:31")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    /**
     * Generates a ResponseEntity containing the API response details.
     *
     * @param status    The HTTP status to be returned.
     * @param body      The response body to include in the response.
     * @param message   A message providing additional information about the response.
     * @param timestamp The time at which the response was generated.
     * @return A ResponseEntity object containing the response details.
     */
    @Operation(summary = "Generate a structured API response",
            description = "Generates a ResponseEntity for API responses, including status, payload, message, and timestamp.")
    public static ResponseEntity<ApiResponseDto> generateResponse(
            @Schema(description = "HTTP status") HttpStatus status,
            @Schema(description = "Response body") Object body,
            @Schema(description = "Additional message") String message,
            @Schema(description = "Timestamp of response") LocalDateTime timestamp) {

        ApiResponseDto response = new ApiResponseDto(status.value(), body, message, timestamp);
        return new ResponseEntity<>(response, status);
    }
}

