package in.theexplorers.quiz.services.impl;

import in.theexplorers.quiz.dtos.request.OptionRequestDto;
import in.theexplorers.quiz.dtos.response.OptionResponseDto;
import in.theexplorers.quiz.entities.Option;
import in.theexplorers.quiz.entities.Question;
import in.theexplorers.quiz.exceptions.ResourceNotFoundException;
import in.theexplorers.quiz.repositories.OptionRepository;
import in.theexplorers.quiz.repositories.QuestionRepository;
import in.theexplorers.quiz.services.OptionService;
import in.theexplorers.quiz.utilities.converters.OptionConverter;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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
    public List<OptionResponseDto> getOptionsByQuestionId(Long questionId) {
        List<Option> options = optionRepository.findByQuestionId(questionId);
        return options.stream().map(optionConverter::optionToOptionResponseDto).toList();
    }

    @Override
    public OptionResponseDto addOption(Long questionId, OptionRequestDto optionRequestDto) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new ResourceNotFoundException("Question not found"));

        Option option = optionConverter.optionRequestDtoToOption(optionRequestDto);
        option.setQuestion(question);
        //todo: remove after applying security
        option.setCreatedBy("SYSTEM");
        option = optionRepository.save(option);

        return optionConverter.optionToOptionResponseDto(option);
    }

    @Transactional
    @Override
    public void deleteOptionById(Long optionId) {
        if (!optionRepository.existsById(optionId)) {
            throw new ResourceNotFoundException("Option not found with ID: " + optionId);
        }
        optionRepository.deleteById(optionId);
    }

    /**
     * Fetches an Option by its ID and converts it to OptionDto.
     *
     * @param id the ID of the option to retrieve.
     * @return the OptionResponseDto representing the option details.
     * @throws ResourceNotFoundException if the option with the given ID does not exist.
     */
    public OptionResponseDto getOptionById(Long id) {
        // Find the option by ID, throw an exception if not found
        Option option = optionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Option not found with id: " + id));

        // Convert the Option entity to a DTO and return
        return optionConverter.optionToOptionResponseDto(option);
    }

    /**
     * Updates an existing option by its ID.
     *
     * @param id               the ID of the option to update.
     * @param optionRequestDto the updated data for the option.
     * @return the updated OptionResponseDto representing the updated option details.
     * @throws ResourceNotFoundException if the option with the given ID does not exist.
     */
    @Override
    public OptionResponseDto updateOptionById(Long id, OptionRequestDto optionRequestDto) {
        // Find the existing option by ID, or throw an exception if not found
        Option existingOption = optionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Option not found with id: " + id));

        // Update the fields of the existing option entity
        optionConverter.optionRequestDtoToOption(optionRequestDto, existingOption);
        // Save the updated option entity to the database
        Option updatedOption = optionRepository.save(existingOption);

        // Convert the updated entity to OptionDto and return it
        return optionConverter.optionToOptionResponseDto(updatedOption);
    }
}

