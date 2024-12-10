package in.theexplorers.quiz.services.impl;

import in.theexplorers.quiz.dtos.common.OptionDto;
import in.theexplorers.quiz.entities.Option;
import in.theexplorers.quiz.entities.Question;
import in.theexplorers.quiz.exceptions.ResourceNotFoundException;
import in.theexplorers.quiz.repositories.OptionRepository;
import in.theexplorers.quiz.repositories.QuestionRepository;
import in.theexplorers.quiz.services.OptionService;
import in.theexplorers.quiz.utilities.converters.OptionConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OptionServiceImpl implements OptionService {
    private final OptionRepository optionRepository;
    private final QuestionRepository questionRepository;
    private final OptionConverter optionConverter;

    public OptionServiceImpl(OptionRepository optionRepository, QuestionRepository questionRepository, OptionConverter optionConverter) {
        this.optionRepository = optionRepository;
        this.questionRepository = questionRepository;
        this.optionConverter = optionConverter;
    }

    @Override
    public List<OptionDto> getOptionsByQuestionId(Long questionId) {
        List<Option> options = optionRepository.findByQuestionId(questionId);
        return options.stream().map(optionConverter::optionToOptionDto).collect(Collectors.toList());
    }

    @Override
    public OptionDto addOption(Long questionId, OptionDto optionDto) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));

        Option option = optionConverter.optionDtoToOption(optionDto);
        option.setQuestion(question);
        option = optionRepository.save(option);

        return optionConverter.optionToOptionDto(option);
    }

    @Override
    public void deleteOptionById(Long optionId) {
        optionRepository.deleteById(optionId);
    }

    /**
     * Fetches an Option by its ID and converts it to OptionDto.
     *
     * @param id the ID of the option to retrieve.
     * @return the OptionDto representing the option details.
     * @throws ResourceNotFoundException if the option with the given ID does not exist.
     */
    public OptionDto getOptionById(Long id) {
        // Find the option by ID, throw an exception if not found
        Option option = optionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Option not found with id: " + id));

        // Convert the Option entity to a DTO and return
        return optionConverter.optionToOptionDto(option);
    }

    /**
     * Updates an existing option by its ID.
     *
     * @param id        the ID of the option to update.
     * @param optionDto the updated data for the option.
     * @return the updated OptionDto representing the updated option details.
     * @throws ResourceNotFoundException if the option with the given ID does not exist.
     */
    public OptionDto updateOptionById(Long id, OptionDto optionDto) {
        // Find the existing option by ID, or throw an exception if not found
        Option existingOption = optionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Option not found with id: " + id));

        // Update the fields of the existing option entity
        existingOption.setText(optionDto.getText());
        existingOption.setIsCorrect(optionDto.getIsCorrect());

        // Save the updated option entity to the database
        Option updatedOption = optionRepository.save(existingOption);

        // Convert the updated entity to OptionDto and return it
        return optionConverter.optionToOptionDto(updatedOption);
    }
}

