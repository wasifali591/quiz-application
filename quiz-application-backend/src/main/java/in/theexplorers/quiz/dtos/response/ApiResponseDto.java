package in.theexplorers.quiz.dtos.response;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * This DTO class serves as a custom response handler for API responses.
 * It is used to generate structured responses for API calls.
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
@Schema(description = "Custom response handler for API responses.")
public class ApiResponseDto {

    /**
     * Generates a ResponseEntity containing the API response details.
     *
     * @param status  The HTTP status to be returned.
     * @param body    The response body to include in the response.
     * @param message A message providing additional information about the response.
     * @return A ResponseEntity object containing the response details.
     */
    @Operation(summary = "Generate a structured API response",
            description = "Generates a ResponseEntity for API responses, including status, payload, and message.")
    public ResponseEntity<Object> generateResponse(
            @Schema(description = "HTTP status code", example = "200") HttpStatus status,
            @Schema(description = "Response body", example = "{\"data\": \"example\"}") Object body,
            @Schema(description = "Additional message", example = "Request processed successfully") String message) {

        Map<String, Object> map = new HashMap<>();
        map.put("status", status.value());
        map.put("payload", body);
        map.put("message", message);
        return new ResponseEntity<>(map, status);
    }
}
