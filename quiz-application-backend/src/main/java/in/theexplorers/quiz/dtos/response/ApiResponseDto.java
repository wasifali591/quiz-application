package in.theexplorers.quiz.dtos.response;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a dto class for custom response handler.
 * It is used to generate response.
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
public class ApiResponseDto {
    public ResponseEntity<Object> generateResponse(HttpStatus status, Object body, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status.value());
        map.put("payload", body);
        map.put("message", message);
        return new ResponseEntity<>(map, status);
    }
}
