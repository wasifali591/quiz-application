package in.theexplorers.quiz.dtos.request;
/*
 * Copyright (c) 2024 TheExplorers.
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Md Wasif Ali
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizActivationDto {
    private Long quizId;
}
