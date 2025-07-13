package in.theexplorers.quiz.dtos.request;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO used to create a new quiz.
 * This class contains all the required fields for quiz creation,
 * excluding audit fields and system-generated values.
 *
 * @author Md Wasif Ali
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO for creating a new quiz.")
public class QuizRequestDto {

    @NotBlank(message = "Title is mandatory")
    @Schema(description = "Title of the quiz", example = "Java Basics Quiz")
    private String title;

    @NotBlank(message = "Description is mandatory")
    @Schema(description = "Description of the quiz", example = "Covers basics of Java programming.")
    private String description;

    @NotNull(message = "Start time is required")
    @Schema(description = "Scheduled start time of the quiz", example = "2025-07-15T10:00:00")
    private LocalDateTime startTime;

    @NotNull(message = "End time is required")
    @Schema(description = "Scheduled end time of the quiz", example = "2025-07-15T10:30:00")
    private LocalDateTime endTime;

    @Schema(description = "Whether the quiz is active", example = "true")
    private Boolean isActive = true;
}
