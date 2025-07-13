package in.theexplorers.quiz.utilities.converters.impl;

import in.theexplorers.quiz.dtos.request.QuizRequestDto;
import in.theexplorers.quiz.dtos.response.QuizDto;
import in.theexplorers.quiz.entities.Quiz;
import in.theexplorers.quiz.utilities.converters.QuizConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
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
     * Converts a QuizRequestDto object to a Quiz entity.
     *
     * @param quizRequestDto The DTO containing quiz creation details.
     * @return The corresponding Quiz entity.
     */
    @Override
    public Quiz quizRequestDtoToQuiz(QuizRequestDto quizRequestDto) {
        return modelMapper.map(quizRequestDto, Quiz.class);
    }

    /**
     * Maps the updated quiz data from DTO to the existing Quiz entity.
     *
     * @param quizRequestDto DTO containing updated quiz details.
     * @param quiz           Existing Quiz entity to update.
     * @return Updated Quiz entity.
     */
    @Override
    public Quiz quizRequestDtoToQuiz(QuizRequestDto quizRequestDto, Quiz quiz) {
        modelMapper.map(quizRequestDto, quiz);
        return quiz;
    }

    /**
     * Maps the Quiz entity data to the given QuizRequestDto.
     *
     * @param quiz           The Quiz entity containing data.
     * @param quizRequestDto The DTO to populate with quiz data.
     * @return Populated QuizRequestDto.
     */
    @Override
    public QuizRequestDto quizToQuizRequestDto(Quiz quiz, QuizRequestDto quizRequestDto) {
        modelMapper.map(quiz, quizRequestDto);
        return quizRequestDto;
    }

    /**
     * Converts a QuizDto object to a QuizRequestDto entity.
     *
     * @param quizDto The DTO containing quiz details.
     * @return The corresponding QuizRequestDto
     */
    @Override
    public QuizRequestDto quizDtoToQuizRequestDto(QuizDto quizDto) {
        return modelMapper.map(quizDto, QuizRequestDto.class);
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
