package in.theexplorers.quiz.utilities.converters.impl;

import in.theexplorers.quiz.dtos.common.QuestionDto;
import in.theexplorers.quiz.dtos.request.QuestionRequestDto;
import in.theexplorers.quiz.dtos.response.QuestionResponseDto;
import in.theexplorers.quiz.entities.Question;
import in.theexplorers.quiz.utilities.converters.QuestionConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class QuestionConverterImpl implements QuestionConverter {
    private final ModelMapper modelMapper;

    public QuestionConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * @param question
     * @return
     */
    @Override
    public QuestionDto questionToQuestionDto(Question question) {
        return modelMapper.map(question, QuestionDto.class);
    }

    /**
     * @param question
     * @return
     */
    @Override
    public QuestionResponseDto questionToQuestionResponseDto(Question question) {
        return modelMapper.map(question, QuestionResponseDto.class);
    }

    /**
     * @param questionRequestDto
     * @param question
     * @return
     */
    @Override
    public Question questionRequestDtoToQuestion(QuestionRequestDto questionRequestDto, Question question) {
        modelMapper.map(questionRequestDto, question);
        return question;
    }

    /**
     * @param questionDto
     * @return
     */
    @Override
    public Question questionDtoToQuestion(QuestionDto questionDto) {
        return modelMapper.map(questionDto, Question.class);
    }

    /**
     * @param questionRequestDto
     * @return
     */
    @Override
    public Question questionRequestDtoToQuestion(QuestionRequestDto questionRequestDto) {
        return modelMapper.map(questionRequestDto, Question.class);
    }

    /**
     * @param questionDto
     * @param question
     * @return
     */
    @Override
    public Question toUpdateQuestion(QuestionDto questionDto, Question question) {
        modelMapper.map(questionDto, question);
        return question;
    }
}
