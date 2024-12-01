package in.theexplorers.quiz.services.impl;

import in.theexplorers.quiz.dtos.common.QuestionDto;
import in.theexplorers.quiz.entities.Question;
import in.theexplorers.quiz.exceptions.ResourceNotFoundException;
import in.theexplorers.quiz.repositories.QuestionRepository;
import in.theexplorers.quiz.services.QuestionService;
import in.theexplorers.quiz.utilities.converters.QuestionConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionConverter questionConverter;

    public QuestionServiceImpl(QuestionRepository questionRepository, QuestionConverter questionConverter) {
        this.questionRepository = questionRepository;
        this.questionConverter = questionConverter;
    }

    @Override
    public List<QuestionDto> getAllQuestions() {
        return questionRepository.findAll().stream()
                .map(questionConverter::questionToQuestionDto)
                .collect(Collectors.toList());
    }

    @Override
    public QuestionDto getQuestionById(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));
        return questionConverter.questionToQuestionDto(question);
    }

    @Override
    public QuestionDto createQuestion(QuestionDto questionDto) {
        Question question = questionConverter.questionDtoToQuestion(questionDto);
        question = questionRepository.save(question);
        return questionConverter.questionToQuestionDto(question);
    }

    @Override
    public QuestionDto updateQuestion(Long questionId, QuestionDto questionDto) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));

        question.setText(questionDto.getText());
        question = questionRepository.save(question);

        return questionConverter.questionToQuestionDto(question);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }
}

