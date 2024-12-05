package in.theexplorers.quiz.services.impl;

import in.theexplorers.quiz.dtos.common.AnswerDto;
import in.theexplorers.quiz.dtos.common.OptionDto;
import in.theexplorers.quiz.dtos.common.QuestionDto;
import in.theexplorers.quiz.entities.Answer;
import in.theexplorers.quiz.entities.Option;
import in.theexplorers.quiz.entities.Question;
import in.theexplorers.quiz.exceptions.ResourceNotFoundException;
import in.theexplorers.quiz.repositories.AnswerRepository;
import in.theexplorers.quiz.repositories.OptionRepository;
import in.theexplorers.quiz.repositories.QuestionRepository;
import in.theexplorers.quiz.services.QuestionService;
import in.theexplorers.quiz.utilities.converters.AnswerConverter;
import in.theexplorers.quiz.utilities.converters.OptionConverter;
import in.theexplorers.quiz.utilities.converters.QuestionConverter;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;
    private final QuestionConverter questionConverter;
    private final OptionConverter optionConverter;
    private final AnswerRepository answerRepository;
    private final AnswerConverter answerConverter;

    public QuestionServiceImpl(QuestionRepository questionRepository, OptionRepository optionRepository, QuestionConverter questionConverter, OptionConverter optionConverter, AnswerRepository answerRepository, AnswerConverter answerConverter) {
        this.questionRepository = questionRepository;
        this.optionRepository = optionRepository;
        this.questionConverter = questionConverter;
        this.optionConverter = optionConverter;
        this.answerRepository = answerRepository;
        this.answerConverter = answerConverter;
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

    /**
     * Adds an option to a specific question.
     * <p>
     * This method finds the question by its ID, creates a new option using the provided
     * OptionDto, and associates it with the question. The option is then saved to the database.
     *
     * @param questionId The ID of the question to which the option will be added.
     * @param optionDto  The OptionDto containing the details of the option to be added.
     * @return The created OptionDto with the persisted option details.
     * @throws IllegalArgumentException if the question with the provided ID is not found.
     */
    @Override
    @Transactional
    public OptionDto addOptionToQuestion(Long questionId, OptionDto optionDto) {
        // Fetch the question by its ID
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);

        if (optionalQuestion.isEmpty()) {
            throw new IllegalArgumentException("Question not found with ID: " + questionId);
        }

        // Retrieve the question entity from the Optional
        Question question = optionalQuestion.get();

        // Create a new Option entity from the OptionDto
        Option option = new Option();
        option.setText(optionDto.getText());
        option.setIsCorrect(optionDto.getIsCorrect());
        option.setQuestion(question);  // Associate the option with the question

        // Save the option to the database
        Option savedOption = optionRepository.save(option);

        // Convert the saved Option entity back to OptionDto and return
        return optionConverter.optionToOptionDto(savedOption);
    }

    /**
     * Retrieves all options for a specific question.
     * <p>
     * This method fetches a question by its ID, retrieves its associated options,
     * and converts them into a list of OptionDto objects.
     *
     * @param questionId The ID of the question for which the options are to be retrieved.
     * @return A list of OptionDto objects representing the options for the specified question.
     * @throws IllegalArgumentException if the question with the provided ID is not found.
     */
    @Override
    public List<OptionDto> getOptionsForQuestion(Long questionId) {
        // Fetch the question by its ID
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);

        // Throw an exception if the question does not exist
        if (optionalQuestion.isEmpty()) {
            throw new IllegalArgumentException("Question not found with ID: " + questionId);
        }

        // Retrieve the question entity
        Question question = optionalQuestion.get();

        // Fetch all options associated with the question
        List<Option> options = optionRepository.findByQuestion(question);

        // Convert the Option entities to OptionDto objects
        return options.stream()
                .map(option -> new OptionDto(option.getId(), option.getText(), option.getIsCorrect(), option.getIsActive()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all answers submitted for a specific question by its ID.
     *
     * @param questionId the ID of the question for which answers are being retrieved.
     * @return a list of {@link AnswerDto} objects containing the details of the answers.
     * @throws ResourceNotFoundException if the question with the given ID is not found.
     */
    @Override
    public List<AnswerDto> getAnswersForQuestion(Long questionId) {
        // Log the start of the method execution
        log.info("Fetching answers for question ID: {}", questionId);

        // Fetch the question entity by ID, or throw an exception if not found
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with ID: " + questionId));

        // Retrieve all answers related to the question
        List<Answer> answers = answerRepository.findByQuestion(question);

        // Convert the list of Answer entities to a list of AnswerDto objects using AnswerConverter
        List<AnswerDto> answerDtos = answers.stream()
                .map(answerConverter::answerToAnswerDto) // Convert each Answer entity to AnswerDto
                .toList();

        // Log the successful retrieval
        log.info("Retrieved {} answers for question ID: {}", answerDtos.size(), questionId);

        return answerDtos;
    }

}

