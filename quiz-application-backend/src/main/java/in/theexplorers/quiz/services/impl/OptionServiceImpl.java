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
}

