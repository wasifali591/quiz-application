package in.theexplorers.quiz.utilities.converters.impl;

import in.theexplorers.quiz.dtos.common.OptionDto;
import in.theexplorers.quiz.dtos.request.OptionRequestDto;
import in.theexplorers.quiz.dtos.response.OptionResponseDto;
import in.theexplorers.quiz.entities.Option;
import in.theexplorers.quiz.utilities.converters.OptionConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OptionConverterImpl implements OptionConverter {
    private final ModelMapper modelMapper;

    public OptionConverterImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * @param option
     * @return
     */
    @Override
    public OptionDto optionToOptionDto(Option option) {
        return modelMapper.map(option, OptionDto.class);
    }

    /**
     * @param optionDto
     * @return
     */
    @Override
    public Option optionDtoToOption(OptionDto optionDto) {
        return modelMapper.map(optionDto, Option.class);
    }

    /**
     * @param optionRequestDto
     * @return
     */
    @Override
    public Option optionRequestDtoToOption(OptionRequestDto optionRequestDto) {
        return modelMapper.map(optionRequestDto, Option.class);
    }

    /**
     * @param option
     * @return
     */
    @Override
    public OptionResponseDto optionToOptionResponseDto(Option option) {
        return modelMapper.map(option, OptionResponseDto.class);
    }

    /**
     * @param optionDto
     * @param option
     * @return
     */
    @Override
    public Option toUpdateOption(OptionDto optionDto, Option option) {
        modelMapper.map(optionDto, option);
        return option;
    }
}
