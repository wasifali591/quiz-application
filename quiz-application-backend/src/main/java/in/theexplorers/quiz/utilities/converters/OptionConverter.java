package in.theexplorers.quiz.utilities.converters;

import in.theexplorers.quiz.dtos.common.OptionDto;
import in.theexplorers.quiz.dtos.request.OptionRequestDto;
import in.theexplorers.quiz.dtos.response.OptionResponseDto;
import in.theexplorers.quiz.entities.Option;

public interface OptionConverter {
    OptionDto optionToOptionDto(Option option);


    Option optionDtoToOption(OptionDto optionDto);

    Option optionRequestDtoToOption(OptionRequestDto optionRequestDto);

    OptionResponseDto optionToOptionResponseDto(Option option);


    Option toUpdateOption(OptionDto optionDto, Option option);
}
