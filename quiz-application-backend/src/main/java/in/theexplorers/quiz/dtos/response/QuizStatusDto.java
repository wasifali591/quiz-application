package in.theexplorers.quiz.dtos.response;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO representing the status of a user's participation in a quiz.
 *
 * @author Md Wasif Ali
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizStatusDto {

    @Schema(description = "Unique identifier for the quiz", example = "1")
    private Long quizId; // ID of the quiz

    @Schema(description = "Unique identifier for the user", example = "42")
    private Long userId; // ID of the user

    @Schema(description = "Flag indicating whether the quiz has been started", example = "true")
    private boolean started; // Indicates if the quiz has started

    @Schema(description = "Flag indicating whether the quiz has been completed", example = "false")
    private boolean completed; // Indicates if the quiz is completed

    @Schema(description = "Progress percentage of the quiz", example = "60")
    private int progressPercentage; // Progress percentage in the quiz

    @Schema(description = "Timestamp when the quiz was started", example = "2024-12-11T10:00:00")
    private LocalDateTime startedAt; // Timestamp when the quiz was started

    @Schema(description = "Timestamp when the quiz was completed (null if not completed)", example = "null")
    private LocalDateTime completedAt; // Timestamp when the quiz was completed
}
