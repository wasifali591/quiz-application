package in.theexplorers.quiz.dtos.request;

import in.theexplorers.quiz.dtos.common.AnswerDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuizSubmissionDto {
    private Long userId; // The user submitting the quiz
    private List<AnswerDto> answers; // List of answers submitted for the quiz
}

