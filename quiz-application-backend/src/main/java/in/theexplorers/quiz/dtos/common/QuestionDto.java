package in.theexplorers.quiz.dtos.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionDto {
    private Long id;
    private String text;
    private Boolean isActive;
    private String createdBy;
    private String updatedBy;
}
