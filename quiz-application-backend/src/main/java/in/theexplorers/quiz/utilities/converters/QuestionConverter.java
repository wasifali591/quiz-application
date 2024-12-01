package in.theexplorers.quiz.utilities.converters;

import in.theexplorers.quiz.dtos.common.QuestionDto;
import in.theexplorers.quiz.entities.Question;

public interface QuestionConverter {

    QuestionDto questionToQuestionDto(Question question);


    Question questionDtoToQuestion(QuestionDto questionDto);


    Question toUpdateQuestion(QuestionDto questionDto, Question question);
}
