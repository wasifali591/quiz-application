package in.theexplorers.quiz.entities;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

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
@Schema(description = "Represents an answer submitted by a user for a quiz question")
@Table(name = "answer")
public class Answer {
    /**
     * Unique identifier for each answer record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for each answer", example = "1")
    private Long id;

    /**
     * The user who submitted this answer.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Schema(description = "The user who submitted this answer", example = "User object representing the submitting user")
    private User user;

    /**
     * The question to which this answer corresponds.
     */
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    @Schema(description = "The question to which this answer corresponds", example = "Question object for the quiz question")
    private Question question;

    /**
     * The option selected by the user as their answer.
     */
    @Column(nullable = false)
    @Schema(description = "The option selected by the user as their answer", example = "Option A")
    private String selectedAnswer;

    /**
     * Indicates whether the answer record is active. Default is true.
     */
    @Builder.Default
    @Schema(description = "Indicates whether the answer is active", defaultValue = "true", example = "true")
    private Boolean isActive = true;

    /**
     * Username of the user who created this record, non-updatable.
     */
    @Column(nullable = false, updatable = false)
    @Schema(description = "Username of the creator of this record", example = "admin")
    private String createdBy;

    /**
     * Username of the user who last updated this record.
     */
    @Schema(description = "Username of the user who last updated this record", example = "editor")
    private String updatedBy;

    /**
     * Timestamp indicating when the answer record was created.
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @Schema(description = "Timestamp when the answer was created", example = "2024-11-04T09:00:00Z")
    private Date createdDate;

    /**
     * Timestamp indicating when the answer record was last updated.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Timestamp when the answer was last updated", example = "2024-11-04T10:00:00Z")
    private Date updatedDate;
}
