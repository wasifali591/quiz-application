package in.theexplorers.quiz.entities;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.entities.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity representing a user in the quiz application.
 *
 * <p>This entity stores information about each user, including credentials, role,
 * and status. Users can either be an <b>ADMIN</b> (with privileges to manage
 * quizzes and user data) or a <b>USER</b> (who participates in quizzes).</p>
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
@Table(name = "app_user")
public class User {

    /**
     * It represents the unique id of every record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * List of quiz attempts made by the user.
     * Each entry represents the user's participation in a specific quiz,
     * including details like status, score, and timestamps.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserQuiz> quizAttempts;

    /**
     * List of answers given by the user
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;

    /**
     * The name of the user.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The unique email of the user.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * The encrypted password for user authentication.
     */
    @Column(nullable = false)
    private String password;

    /**
     * The role of the user (either ADMIN or USER).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    /**
     * Indicates whether the user account is active. Default is true.
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
