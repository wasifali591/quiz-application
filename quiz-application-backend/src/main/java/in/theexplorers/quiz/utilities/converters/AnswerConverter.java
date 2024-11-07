package in.theexplorers.quiz.utilities.converters;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.dtos.common.AnswerDto;
import in.theexplorers.quiz.entities.Answer;

/**
 * Converter interface for transforming between {@link Answer} entity and {@link AnswerDto} data transfer object.
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
public interface AnswerConverter {

    /**
     * Converts an {@link Answer} entity to an {@link AnswerDto} data transfer object.
     *
     * @param answer the {@link Answer} entity to be converted
     * @return an {@link AnswerDto} containing the data from the given {@link Answer} entity
     */
    AnswerDto answerToAnswerDto(Answer answer);

    /**
     * Converts an {@link AnswerDto} data transfer object to an {@link Answer} entity.
     *
     * @param answerDto the {@link AnswerDto} object to be converted
     * @return an {@link Answer} entity containing the data from the given {@link AnswerDto}
     */
    Answer answerDtoToAnswer(AnswerDto answerDto);

    /**
     * Converts an {@link AnswerDto} data transfer object to an {@link Answer} entity.
     *
     * @param answerDto the {@link AnswerDto} object to be converted
     * @param answer    object to be converted to the {@link Answer}
     * @return an {@link Answer} entity containing the data from the given {@link AnswerDto}
     */
    Answer toUpdateAnswer(AnswerDto answerDto, Answer answer);
}
