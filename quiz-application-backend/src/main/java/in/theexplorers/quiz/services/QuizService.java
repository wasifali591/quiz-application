package in.theexplorers.quiz.services;

import in.theexplorers.quiz.dtos.request.QuizSubmissionDto;
import in.theexplorers.quiz.dtos.response.QuizDto;
import in.theexplorers.quiz.dtos.response.QuizResultDto;

import java.util.List;

public interface QuizService {
    List<QuizDto> getAllQuizzes();

    QuizDto getQuizById(Long quizId);

    QuizDto createQuiz(QuizDto quizDto);

    QuizDto updateQuiz(Long quizId, QuizDto quizDto);

    void deleteQuiz(Long quizId);

    void addQuestionsToQuiz(Long quizId, List<Long> questionIds);

    QuizResultDto evaluateQuiz(Long quizId, QuizSubmissionDto submission);
}
