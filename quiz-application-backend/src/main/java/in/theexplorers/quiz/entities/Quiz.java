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

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Entity representing a quiz in the application.
 *
 * <p>This entity stores information about each quiz, including title, scheduled start
 * and end times, associated questions, and audit details for record management.</p>
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
@Schema(description = "Represents a quiz, including title, start and end times, associated questions, and auditing information.")
@Table(name = "quiz")
public class Quiz {

    /**
     * It represents the unique id of every record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for each quiz record.")
    private Long id;

    /**
     * The name or title of the quiz.
     */
    @Column(nullable = false)
    @Schema(description = "The name or title of the quiz.", example = "General Knowledge Quiz")
    private String title;

    /**
     * The description of the quiz.
     */
    @Column(nullable = false)
    @Schema(description = "The description of the quiz.", example = "General Knowledge Quiz")
    private String description;

    /**
     * The scheduled start time of the quiz.
     */
    @Column(nullable = false)
    @Schema(description = "The scheduled start time of the quiz.")
    private LocalDateTime startTime;

    /**
     * The scheduled end time of the quiz.
     */
    @Column(nullable = false)
    @Schema(description = "The scheduled end time of the quiz.")
    private LocalDateTime endTime;

    /**
     * List of questions associated with this quiz.
     */
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Schema(description = "List of questions associated with this quiz.")
    private List<Question> questions;

    /**
     * Indicates whether the quiz is active. Default is true.
     */
    @Builder.Default
    @Schema(description = "Indicates whether the quiz is active. Default is true.", defaultValue = "true")
    private Boolean isActive = true;

    /**
     * The username of the user who created this record.
     */
    @Column(nullable = false, updatable = false)
    @Schema(description = "The username of the user who created this record.", example = "admin")
    private String createdBy;

    /**
     * The username of the user who last updated this record.
     */
    @Schema(description = "The username of the user who last updated this record.", example = "admin")
    private String updatedBy;

    /**
     * The timestamp when the quiz record was created.
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @Schema(description = "The timestamp when the quiz record was created.")
    private Date createdDate;

    /**
     * The timestamp when the quiz record was last updated.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "The timestamp when the quiz record was last updated.")
    private Date updatedDate;
}
