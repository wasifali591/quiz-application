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
 * Entity representing an answer option for a question in a quiz.
 *
 * <p>This entity stores the details of a specific answer option, including its text,
 * association with a question, and audit fields for tracking creation and updates.</p>
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Data
@Builder
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
     * Indicates whether the option is active. Default is true.
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
     * Timestamp indicating when the option record was created.
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdDate;

    /**
     * Timestamp indicating when the option record was last updated.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
}
