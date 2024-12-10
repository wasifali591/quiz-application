package in.theexplorers.quiz.services;

import in.theexplorers.quiz.dtos.common.OptionDto;

import java.util.List;

public interface OptionService {
    List<OptionDto> getOptionsByQuestionId(Long questionId);

    OptionDto addOption(Long questionId, OptionDto optionDto);

    void deleteOptionById(Long optionId);

    /**
     * Fetches an Option by its ID and converts it to OptionDto.
     *
     * @param id the ID of the option to retrieve.
     * @return the OptionDto representing the option details.
     */
    OptionDto getOptionById(Long id);

    /**
     * Updates an existing option by its ID.
     *
     * @param id        the ID of the option to update.
     * @param optionDto the updated data for the option.
     * @return the updated OptionDto representing the updated option details.
     */
    OptionDto updateOptionById(Long id, OptionDto optionDto);
}

