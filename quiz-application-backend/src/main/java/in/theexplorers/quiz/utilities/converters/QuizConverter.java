package in.theexplorers.quiz.utilities.converters;

import in.theexplorers.quiz.dtos.request.QuizRequestDto;
import in.theexplorers.quiz.dtos.response.QuizDto;
import in.theexplorers.quiz.entities.Quiz;

public interface QuizConverter {
    QuizDto quizToQuizDto(Quiz quiz);

    /**
     * Converts a QuizRequestDto object to a Quiz entity.
     *
     * @param quizRequestDto The DTO containing quiz creation details.
     * @return The corresponding Quiz entity.
     */
    Quiz quizRequestDtoToQuiz(QuizRequestDto quizRequestDto);

    /**
     * Maps the updated quiz data from DTO to the existing Quiz entity.
     *
     * @param quizRequestDto DTO containing updated quiz details.
     * @param quiz           Existing Quiz entity to update.
     * @return Updated Quiz entity.
     */
    Quiz quizRequestDtoToQuiz(QuizRequestDto quizRequestDto, Quiz quiz);

    /**
     * Maps the Quiz entity data to the given QuizRequestDto.
     *
     * @param quiz           The Quiz entity containing data.
     * @param quizRequestDto The DTO to populate with quiz data.
     * @return Populated QuizRequestDto.
     */
    QuizRequestDto quizToQuizRequestDto(Quiz quiz, QuizRequestDto quizRequestDto);

    /**
     * Converts a QuizDto object to a QuizRequestDto entity.
     *
     * @param quizDto The DTO containing quiz details.
     * @return The corresponding QuizRequestDto
     */
    QuizRequestDto quizDtoToQuizRequestDto(QuizDto quizDto);


    Quiz quizDtoToQuiz(QuizDto quizDto);


    Quiz toUpdateQuiz(QuizDto quizDto, Quiz quiz);
}
