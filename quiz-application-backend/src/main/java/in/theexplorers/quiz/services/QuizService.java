package in.theexplorers.quiz.services;

import in.theexplorers.quiz.dtos.common.QuestionDto;
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

    /**
     * Adds a question to a specific quiz.
     *
     * @param quizId      The ID of the quiz.
     * @param questionDto The details of the question to add.
     * @return The added question as a QuestionDto.
     */
    QuestionDto addQuestionToQuiz(Long quizId, QuestionDto questionDto);

    QuizResultDto evaluateQuiz(Long quizId, QuizSubmissionDto submission);

    List<QuizDto> findInactiveQuizzesWithinTimeRange();
}
