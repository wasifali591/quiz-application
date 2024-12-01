package in.theexplorers.quiz.services;

import in.theexplorers.quiz.dtos.common.QuestionDto;

import java.util.List;

public interface QuestionService {
    List<QuestionDto> getAllQuestions();

    QuestionDto getQuestionById(Long questionId);

    QuestionDto createQuestion(QuestionDto questionDto);

    QuestionDto updateQuestion(Long questionId, QuestionDto questionDto);

    void deleteQuestion(Long questionId);
}
