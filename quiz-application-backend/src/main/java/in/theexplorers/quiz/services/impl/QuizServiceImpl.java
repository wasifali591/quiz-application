package in.theexplorers.quiz.services.impl;

import in.theexplorers.quiz.dtos.request.QuizSubmissionDto;
import in.theexplorers.quiz.dtos.response.QuizDto;
import in.theexplorers.quiz.dtos.response.QuizResultDto;
import in.theexplorers.quiz.entities.Question;
import in.theexplorers.quiz.entities.Quiz;
import in.theexplorers.quiz.exceptions.ResourceNotFoundException;
import in.theexplorers.quiz.repositories.QuestionRepository;
import in.theexplorers.quiz.repositories.QuizRepository;
import in.theexplorers.quiz.services.QuizService;
import in.theexplorers.quiz.utilities.converters.QuizConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    private final QuizConverter quizConverter;

    public QuizServiceImpl(QuizRepository quizRepository, QuestionRepository questionRepository, QuizConverter quizConverter) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.quizConverter = quizConverter;
    }

    @Override
    public List<QuizDto> getAllQuizzes() {
        return quizRepository.findAll().stream()
                .map(quizConverter::quizToQuizDto)
                .collect(Collectors.toList());
    }

    @Override
    public QuizDto getQuizById(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));
        return quizConverter.quizToQuizDto(quiz);
    }

    @Override
    public QuizDto createQuiz(QuizDto quizDto) {
        Quiz quiz = quizConverter.quizDtoToQuiz(quizDto);
        quiz = quizRepository.save(quiz);
        return quizConverter.quizToQuizDto(quiz);
    }

    @Override
    public QuizDto updateQuiz(Long quizId, QuizDto quizDto) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));

        quiz.setTitle(quizDto.getTitle());
        quiz.setDescription(quizDto.getDescription());
        quiz.setStartTime(quizDto.getStartTime());
        quiz.setEndTime(quizDto.getEndTime());
        quiz = quizRepository.save(quiz);

        return quizConverter.quizToQuizDto(quiz);
    }

    @Override
    public void deleteQuiz(Long quizId) {
        quizRepository.deleteById(quizId);
    }

    @Override
    public void addQuestionsToQuiz(Long quizId, List<Long> questionIds) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found"));

        List<Question> questions = questionRepository.findAllById(questionIds);
        quiz.getQuestions().addAll(questions);
        quizRepository.save(quiz);
    }

    @Override
    public QuizResultDto evaluateQuiz(Long quizId, QuizSubmissionDto submission) {
        // Evaluate logic
        return QuizResultDto.builder()
                .quizId(quizId)
                .userId(submission.getUserId())
                .correctAnswers(5) // Example: Computed based on submission
                .totalQuestions(10) // Example: Quiz question count
                .score(50) // Example: Computed score
                .build();
    }
}

