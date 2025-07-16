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
@Table(name = "option")
public class Option {

    /**
     * Unique identifier for each option record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Text content of the option.
     */
    @Column(nullable = false)
    private String text;

    /**
     * The question to which this option belongs.
     */
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    /**
     * Flag indicating if this option is the correct answer.
     */
    @Column(nullable = false)
    private Boolean isCorrect = false;

    /**
     * Indicates whether the option is active. Default is true.
     */
    @Builder.Default
    @Column(nullable = false)
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
     * Timestamp indicating when the option record was created.
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private LocalDateTime createdOn;

    /**
     * Timestamp indicating when the option record was last updated.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedOn;
}
