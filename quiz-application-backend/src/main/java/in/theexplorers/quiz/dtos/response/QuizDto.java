package in.theexplorers.quiz.dtos.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class QuizDto {
    private Long id;
    private String name;
    private String description;
    private Date startTime;
    private Date endTime;
    private List<Long> questionIds; // List of associated question IDs
    private List<Long> participantIds; // List of user IDs who are participating
}
