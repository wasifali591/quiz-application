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

/**
 * Entity representing a user's answer to a quiz question.
 *
 * <p>This entity stores the details of an answer submitted by a user for a specific question,
 * including the selected answer, user association, and audit fields for tracking creation and updates.</p>
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
@Builder
@Entity
@Data
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
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The question to which this answer corresponds.
     */
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    /**
     * The option selected by the user as their answer.
     */
    @Column(nullable = false)
    private String selectedAnswer;

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
    private Date createdDate;

    /**
     * Timestamp indicating when the answer record was last updated.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
}