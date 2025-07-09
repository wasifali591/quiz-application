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
 * Entity representing options for a question in a quiz.
 *
 * <p>This entity stores the details of a specific answer option, including its text,
 * association with a question, and audit fields for tracking creation and updates.</p>
 *
 * @author Md Wasif Ali
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents an option for a quiz question")
@Table(name = "option")
public class Option {

    /**
     * Unique identifier for each option record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for each option", example = "1")
    private Long id;

    /**
     * Text content of the option.
     */
    @Column(nullable = false)
    @Schema(description = "Text content of the option", example = "Option A")
    private String text;

    /**
     * The question to which this option belongs.
     */
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    @Schema(description = "The question to which this option belongs", example = "Question object for the quiz question")
    private Question question;

    /**
     * Flag indicating if this option is the correct answer.
     */
    @Column(nullable = false)
    @Schema(description = "Flag indicating if this option is the correct answer", example = "true")
    private Boolean isCorrect = false;

    /**
     * Indicates whether the option is active. Default is true.
     */
    @Builder.Default
    @Column(nullable = false)
    @Schema(description = "Indicates whether the option is active", defaultValue = "true", example = "true")
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
     * Timestamp indicating when the option record was created.
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @Schema(description = "Timestamp when the option was created", example = "2024-11-04T09:00:00Z")
    private Date createdDate;

    /**
     * Timestamp indicating when the option record was last updated.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Timestamp when the option was last updated", example = "2024-11-04T10:00:00Z")
    private Date updatedDate;
}
