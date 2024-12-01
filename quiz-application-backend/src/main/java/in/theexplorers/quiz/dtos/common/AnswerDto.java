package in.theexplorers.quiz.dtos.common;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * DTO representing an answer in the quiz application.
 *
 * <p>This DTO is used to transfer answer data between the application and clients.
 * It includes essential fields such as the answer ID, the user's selected answer,
 * and its active status.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     AnswerDto answerDto = new AnswerDto(1L, "Option A", true);
 * </pre>
 *
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Builder
@Schema(description = "Data Transfer Object for representing an answer in the quiz application.")
public class AnswerDto {
    private Long id;
    private Long questionId;
    private Long userId;
    private String selectedAnswer;
    private Boolean isActive;
    private String createdBy;
    private String updatedBy;
}

