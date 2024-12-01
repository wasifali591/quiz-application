package in.theexplorers.quiz.utilities.converters;

import in.theexplorers.quiz.dtos.common.OptionDto;
import in.theexplorers.quiz.entities.Option;

public interface OptionConverter {
    OptionDto optionToOptionDto(Option option);


    Option optionDtoToOption(OptionDto optionDto);


    Option toUpdateOption(OptionDto optionDto, Option option);
}
