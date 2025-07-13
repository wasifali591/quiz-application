package in.theexplorers.quiz.dtos.response;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Standardized response structure for question.
 *
 * @author Md Wasif Ali
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response DTO representing a quiz question along with its options.")
public class QuestionResponseDto {

    @Schema(description = "Unique identifier for the question", example = "1")
    private Long id;

    @Schema(description = "Text content of the question", example = "What is the capital of France?")
    private String text;

    @Schema(description = "List of answer options for this question")
    private List<OptionResponseDto> options;

    @Schema(description = "Flag indicating if the question is active", example = "true")
    private Boolean isActive;
}
