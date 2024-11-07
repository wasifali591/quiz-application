package in.theexplorers.quiz.utilities.converters.impl;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.dtos.common.AnswerDto;
import in.theexplorers.quiz.entities.Answer;
import in.theexplorers.quiz.utilities.converters.AnswerConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class AnswerConverterImpl implements AnswerConverter {
    private final ModelMapper modelMapper;

    /**
     * Constructs a new {@link AnswerConverterImpl} with the given {@link ModelMapper}.
     *
     * @param modelMapper the {@link ModelMapper} instance used for converting between entities and DTOs
     */
    public AnswerConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Converts an {@link Answer} entity to an {@link AnswerDto} data transfer object.
     *
     * @param answer the {@link Answer} entity to be converted
     * @return an {@link AnswerDto} containing the data from the given {@link Answer} entity
     */
    @Override
    public AnswerDto answerToAnswerDto(Answer answer) {
        return modelMapper.map(answer, AnswerDto.class);
    }

    /**
     * Converts an {@link AnswerDto} data transfer object to an {@link Answer} entity.
     *
     * @param answerDto the {@link AnswerDto} object to be converted
     * @return an {@link Answer} entity containing the data from the given {@link AnswerDto}
     */
    @Override
    public Answer answerDtoToAnswer(AnswerDto answerDto) {
        return modelMapper.map(answerDto, Answer.class);
    }

    /**
     * Converts an {@link AnswerDto} data transfer object to an {@link Answer} entity.
     *
     * @param answerDto the {@link AnswerDto} object to be converted
     * @param answer    object to be converted to the {@link Answer}
     * @return an {@link Answer} entity containing the data from the given {@link AnswerDto}
     */
    @Override
    public Answer toUpdateAnswer(AnswerDto answerDto, Answer answer) {
        modelMapper.map(answerDto, answer);
        return answer;
    }
}
