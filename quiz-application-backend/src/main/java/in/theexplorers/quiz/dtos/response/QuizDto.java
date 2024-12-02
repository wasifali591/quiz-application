package in.theexplorers.quiz.dtos.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class QuizDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Long> questionIds; // List of associated question IDs
    private List<Long> participantIds; // List of user IDs who are participating
}
