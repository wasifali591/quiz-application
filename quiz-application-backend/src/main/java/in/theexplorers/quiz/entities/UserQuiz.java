package in.theexplorers.quiz.entities;

import in.theexplorers.quiz.entities.enums.QuizAttemptStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_quiz", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "quiz_id"})})
public class UserQuiz {

    /**
     * It represents the unique id of every record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user who attempted the quiz.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The quiz that was attempted.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    /**
     * Attempt status (e.g. NOT_STARTED, STARTED, COMPLETED)
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuizAttemptStatus status;

    /**
     * Score obtained in this attempt.
     */
    private Integer score;

    /**
     * Quiz start timestamp for this user.
     */
    private LocalDateTime startedAt;

    /**
     * Quiz end timestamp for this user.
     */
    private LocalDateTime completedAt;

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
     * The timestamp when the user record was created.
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private LocalDateTime createdOn;

    /**
     * The timestamp when the user record was last updated.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedOn;
}
