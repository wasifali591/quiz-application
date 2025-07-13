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
import java.util.List;

/**
 * Entity representing a question in a quiz.
 *
 * <p>This entity stores the details of a question, including its text, associated quiz,
 * possible answer options, and the correct answer. It also includes audit fields for
 * tracking creation and updates.</p>
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
@Schema(description = "Represents the question of the  quiz")
@Table(name = "question")
public class Question {

    /**
     * Unique identifier for each question record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for each question record.")
    private Long id;

    /**
     * Text content of the question, with a maximum length of 500 characters.
     */
    @Column(nullable = false, length = 500)
    @Schema(description = "Text content of the question.", example = "What is the capital of France?", maxLength = 500)
    private String text;

    /**
     * The quiz to which this question belongs.
     */
    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    @Schema(description = "The quiz to which this question belongs.")
    private Quiz quiz;

    /**
     * List of possible answer options associated with this question.
     */
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Schema(description = "List of possible answer options associated with this question.")
    private List<Option> options;

    /**
     * Indicates whether the question is active. Default is true.
     */
    @Builder.Default
    @Schema(description = "Indicates whether the question is active. Default is true.", defaultValue = "true")
    private Boolean isActive = true;

    /**
     * Username of the user who created this record, non-updatable.
     */
    @Column(nullable = false, updatable = false)
    @Schema(description = "Username of the user who created this record.", example = "admin")
    private String createdBy;

    /**
     * Username of the user who last updated this record.
     */
    @Schema(description = "Username of the user who last updated this record.", example = "admin")
    private String updatedBy;

    /**
     * Timestamp indicating when the question record was created.
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @Schema(description = "Timestamp indicating when the question record was created.")
    private Date createdDate;

    /**
     * Timestamp indicating when the question record was last updated.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "Timestamp indicating when the question record was last updated.")
    private Date updatedDate;
}
