package in.theexplorers.quiz.dtos.request;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO used to create a new option.
 * This class contains all the required fields for option creation,
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
@Schema(description = "DTO representing an option for a question")
public class OptionRequestDto {

    @NotBlank(message = "Option text is required")
    @Schema(description = "Text of the option", example = "Paris")
    private String text;

    @Schema(description = "Indicates if this is the correct answer", example = "true", defaultValue = "false")
    private boolean isCorrect;
}

