package in.theexplorers.quiz.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizResultDto {
    private Long userId;
    private Long quizId;
    private Integer totalQuestions;
    private Integer correctAnswers;
    private Integer score;
}

