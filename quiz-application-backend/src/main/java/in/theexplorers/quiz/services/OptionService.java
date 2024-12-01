package in.theexplorers.quiz.services;

import in.theexplorers.quiz.dtos.common.OptionDto;

import java.util.List;

public interface OptionService {
    List<OptionDto> getOptionsByQuestionId(Long questionId);

    OptionDto createOption(Long questionId, OptionDto optionDto);

    void deleteOption(Long optionId);
}

