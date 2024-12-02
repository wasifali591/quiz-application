package in.theexplorers.quiz.utilities.converters;

import in.theexplorers.quiz.dtos.response.QuizDto;
import in.theexplorers.quiz.entities.Quiz;

public interface QuizConverter {
    QuizDto quizToQuizDto(Quiz quiz);


    Quiz quizDtoToQuiz(QuizDto quizDto);


    Quiz toUpdateQuiz(QuizDto quizDto, Quiz quiz);
}
