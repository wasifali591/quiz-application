package in.theexplorers.quiz.utilities.converters.impl;

import in.theexplorers.quiz.dtos.response.QuizDto;
import in.theexplorers.quiz.entities.Quiz;
import in.theexplorers.quiz.utilities.converters.QuizConverter;
import org.modelmapper.ModelMapper;

public class QuizConverterImpl implements QuizConverter {
    private final ModelMapper modelMapper;

    public QuizConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * @param quiz
     * @return
     */
    @Override
    public QuizDto quizToQuizDto(Quiz quiz) {
        return modelMapper.map(quiz, QuizDto.class);
    }

    /**
     * @param quizDto
     * @return
     */
    @Override
    public Quiz quizDtoToQuiz(QuizDto quizDto) {
        return modelMapper.map(quizDto, Quiz.class);
    }

    /**
     * @param quizDto
     * @param quiz
     * @return
     */
    @Override
    public Quiz toUpdateQuiz(QuizDto quizDto, Quiz quiz) {
        modelMapper.map(quizDto, quiz);
        return quiz;
    }
}
