package in.theexplorers.quiz.services;

import in.theexplorers.quiz.dtos.common.QuestionDto;
import in.theexplorers.quiz.dtos.request.QuestionRequestDto;
import in.theexplorers.quiz.dtos.request.QuizRequestDto;
import in.theexplorers.quiz.dtos.request.QuizSubmissionDto;
import in.theexplorers.quiz.dtos.response.QuestionResponseDto;
import in.theexplorers.quiz.dtos.response.QuizDto;
import in.theexplorers.quiz.dtos.response.QuizResultDto;

import java.util.List;

public interface QuizService {
    List<QuizDto> getAllQuizzes();

    QuizDto getQuizById(Long quizId);

    QuizDto createQuiz(QuizRequestDto quizRequestDto);

    /**
     * Updates an existing quiz with the given ID using the provided request data.
     *
     * @param quizId         ID of the quiz to be updated.
     * @param quizRequestDto DTO containing the updated quiz details.
     * @return Updated quiz details.
     */
    QuizDto updateQuiz(Long quizId, QuizRequestDto quizRequestDto);


    void deleteQuiz(Long quizId);

    /**
     * Adds a question to a specific quiz.
     *
     * @param quizId      The ID of the quiz.
     * @param questionRequestDto The details of the question to add.
     * @return The added question as a QuestionDto.
     */
    QuestionResponseDto addQuestionToQuiz(Long quizId, QuestionRequestDto questionRequestDto);

    QuizResultDto evaluateQuiz(Long quizId, QuizSubmissionDto submission);

    List<QuizDto> findInactiveQuizzesWithinTimeRange();
}
