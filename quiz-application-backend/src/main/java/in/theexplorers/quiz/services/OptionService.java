package in.theexplorers.quiz.services;

import in.theexplorers.quiz.dtos.common.OptionDto;
import in.theexplorers.quiz.dtos.request.OptionRequestDto;
import in.theexplorers.quiz.dtos.response.OptionResponseDto;

import java.util.List;

public interface OptionService {
    List<OptionResponseDto> getOptionsByQuestionId(Long questionId);

    OptionResponseDto addOption(Long questionId, OptionRequestDto optionRequestDto);

    void deleteOptionById(Long optionId);

    /**
     * Fetches an Option by its ID and converts it to OptionDto.
     *
     * @param id the ID of the option to retrieve.
     * @return the OptionDto representing the option details.
     */
    OptionResponseDto getOptionById(Long id);

    /**
     * Updates an existing option by its ID.
     *
     * @param id        the ID of the option to update.
     * @param optionRequestDto the updated data for the option.
     * @return the updated OptionResponseDto representing the updated option details.
     */
    OptionResponseDto updateOptionById(Long id, OptionRequestDto optionRequestDto);
}

