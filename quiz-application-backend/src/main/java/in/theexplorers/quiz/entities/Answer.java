package in.theexplorers.quiz.entities;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Entity representing a user's answer to a quiz question.
 *
 * <p>This entity stores the details of an answer submitted by a user for a specific question,
 * including the selected answer, user association, and audit fields for tracking creation and updates.</p>
 *
 * @author Md Wasif Ali
 * @version 1.0.0
 * @since 1.0.0
 */
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "answer", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "question_id"}))
public class Answer {
    /**
     * Unique identifier for each answer record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user who submitted this answer.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The question to which this answer corresponds.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    /**
     * The selected option
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "option_id")
    private Option selectedOption;

    /**
     * Is the answer is correct
     */
    private Boolean isCorrect;

    /**
     * Indicates whether the answer record is active. Default is true.
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
     * Timestamp indicating when the answer record was created.
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private LocalDateTime createdOn;

    /**
     * Timestamp indicating when the answer record was last updated.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedOn;
}
