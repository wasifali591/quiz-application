package in.theexplorers.quiz.entities;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.entities.enums.UserRole;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

/**
 * Entity representing a user in the quiz application.
 *
 * <p>This entity stores information about each user, including credentials, role,
 * and status. Users can either be an <b>ADMIN</b> (with privileges to manage
 * quizzes and user data) or a <b>USER</b> (who participates in quizzes).</p>
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Data
@Builder
public class User {
    /**
     * It represents the unique id of every record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The unique username of the user.
     */
    @Column(nullable = false, unique = true)
    private String username;

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
    private Date createdDate;

    /**
     * The timestamp when the user record was last updated.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
}
