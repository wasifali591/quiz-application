package in.theexplorers.quiz.services;

import in.theexplorers.quiz.dtos.common.AnswerDto;
import in.theexplorers.quiz.dtos.common.OptionDto;
import in.theexplorers.quiz.dtos.common.QuestionDto;
import in.theexplorers.quiz.exceptions.ResourceNotFoundException;

import java.util.List;

public interface QuestionService {
    List<QuestionDto> getAllQuestions();

    QuestionDto getQuestionById(Long questionId);

    QuestionDto createQuestion(QuestionDto questionDto);

    QuestionDto updateQuestion(Long questionId, QuestionDto questionDto);

    void deleteQuestion(Long questionId);

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
    OptionDto addOptionToQuestion(Long questionId, OptionDto optionDto);

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
    List<OptionDto> getOptionsForQuestion(Long questionId);

    /**
     * Retrieves all answers submitted for a specific question by its ID.
     *
     * @param questionId the ID of the question for which answers are being retrieved.
     * @return a list of {@link AnswerDto} objects containing the details of the answers.
     * @throws ResourceNotFoundException if the question with the given ID is not found.
     */
    List<AnswerDto> getAnswersForQuestion(Long questionId);
}
