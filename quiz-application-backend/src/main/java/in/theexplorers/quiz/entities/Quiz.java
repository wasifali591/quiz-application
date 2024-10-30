package in.theexplorers.quiz.entities;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Entity representing a quiz in the application.
 *
 * <p>This entity stores information about each quiz, including title, scheduled start
 * and end times, associated questions, and audit details for record management.</p>
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Data
@Builder
public class Quiz {
    /**
     * It represents the unique id of every record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name or title of the quiz.
     */
    @Column(nullable = false)
    private String title;

    /**
     * The scheduled start time of the quiz.
     */
    @Column(nullable = false)
    private LocalDateTime startTime;

    /**
     * The scheduled end time of the quiz.
     */
    @Column(nullable = false)
    private LocalDateTime endTime;

    /**
     * List of questions associated with this quiz.
     */
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Question> questions;

    /**
     * Indicates whether the quiz is active. Default is true.
     */
    @Builder.Default
    private Boolean isActive = true;

    /**
     * The username of the user who created this record.
     */
    @Column(nullable = false, updatable = false)
    private String createdBy;

    /**
     * The username of the user who last updated this record.
     */
    private String updatedBy;

    /**
     * The timestamp when the quiz record was created.
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdDate;

    /**
     * The timestamp when the quiz record was last updated.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
}
