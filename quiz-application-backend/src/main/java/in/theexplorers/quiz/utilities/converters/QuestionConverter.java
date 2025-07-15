package in.theexplorers.quiz.utilities.converters;

import in.theexplorers.quiz.dtos.common.QuestionDto;
import in.theexplorers.quiz.dtos.request.QuestionRequestDto;
import in.theexplorers.quiz.dtos.response.QuestionResponseDto;
import in.theexplorers.quiz.entities.Question;

public interface QuestionConverter {

    QuestionDto questionToQuestionDto(Question question);

    QuestionResponseDto questionToQuestionResponseDto(Question question);

    Question questionRequestDtoToQuestion(QuestionRequestDto questionRequestDto, Question question);

    Question questionDtoToQuestion(QuestionDto questionDto);

    Question questionRequestDtoToQuestion(QuestionRequestDto questionRequestDto);


    Question toUpdateQuestion(QuestionDto questionDto, Question question);
}
