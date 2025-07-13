package in.theexplorers.quiz.dtos.request;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO used to create a new question.
 * This class contains all the required fields for question creation,
 * excluding audit fields and system-generated values.
 *
 * @author Md Wasif Ali
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for creating or updating a quiz question with options")
public class QuestionRequestDto {

    @NotBlank(message = "Question text is required")
    @Schema(description = "Text of the question", example = "What is the capital of France?")
    private String text;

    @NotEmpty(message = "At least one option is required")
    @Schema(description = "List of answer options for the question")
    private List<OptionRequestDto> options;
}

