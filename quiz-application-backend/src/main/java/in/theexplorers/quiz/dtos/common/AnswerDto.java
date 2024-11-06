package in.theexplorers.quiz.dtos.common;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object for representing an answer in the quiz application.")
public class AnswerDto {

    /**
     * Unique identifier for each answer record.
     */
    @Schema(description = "Unique identifier for the answer", example = "1")
    private Long id;

    /**
     * The option selected by the user as their answer.
     */
    @Schema(description = "The option selected by the user as their answer", example = "Option A")
    private String selectedAnswer;

    /**
     * Indicates whether the answer record is active.
     */
    @Schema(description = "Indicates whether the answer is active", example = "true", defaultValue = "true")
    private Boolean isActive;
}
