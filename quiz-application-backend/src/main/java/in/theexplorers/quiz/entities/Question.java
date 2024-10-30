package in.theexplorers.quiz.entities;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

/**
 * Entity representing a question in a quiz.
 *
 * <p>This entity stores the details of a question, including its text, associated quiz,
 * possible answer options, and the correct answer. It also includes audit fields for
 * tracking creation and updates.</p>
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Data
@Builder
public class Question {

    /**
     * Unique identifier for each question record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Text content of the question, with a maximum length of 500 characters.
     */
    @Column(nullable = false, length = 500)
    private String text;

    /**
     * The quiz to which this question belongs.
     */
    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    /**
     * List of possible answer options associated with this question.
     */
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Option> options;

    /**
     * Correct answer to the question, stored as a string.
     */
    @Column(nullable = false)
    private String correctAnswer;

    /**
     * Indicates whether the question is active. Default is true.
     */
    @Builder.Default
    private Boolean isActive = true;

    /**
     * Username of the user who created this record, non-updatable.
     */
    @Column(nullable = false, updatable = false)
    private String createdBy;

    /**
     * Username of the user who last updated this record.
     */
    private String updatedBy;

    /**
     * Timestamp indicating when the question record was created.
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdDate;

    /**
     * Timestamp indicating when the question record was last updated.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
}