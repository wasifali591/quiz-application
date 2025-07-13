package in.theexplorers.quiz.dtos.response;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Standardized response structure for options.
 *
 * @author Md Wasif Ali
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response DTO representing a quiz option.")
public class OptionResponseDto {

    @Schema(description = "Unique identifier for the option", example = "101")
    private Long id;

    @Schema(description = "Text content of the option", example = "Paris")
    private String text;

    @Schema(description = "Flag indicating if this is the correct answer", example = "true")
    private Boolean isCorrect;

    @Schema(description = "Whether the option is active", example = "true")
    private Boolean isActive;
}

