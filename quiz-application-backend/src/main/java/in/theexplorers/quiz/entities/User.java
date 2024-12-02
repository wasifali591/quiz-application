package in.theexplorers.quiz.entities;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import in.theexplorers.quiz.entities.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Represents a user in the quiz application, including credentials, role, and status.")
public class User {

    /**
     * It represents the unique id of every record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for each user record.")
    private Long id;

    /**
     * The name of the user.
     */
    @Column(nullable = false)
    @Schema(description = "The name of the user.", example = "john doe")
    private String name;

    /**
     * The unique email of the user.
     */
    @Column(nullable = false, unique = true)
    @Schema(description = "The unique email of the user.", example = "johndoe@mail.com")
    private String email;

    /**
     * The encrypted password for user authentication.
     */
    @Column(nullable = false)
    @Schema(description = "The encrypted password for user authentication.")
    private String password;

    /**
     * The role of the user (either ADMIN or USER).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "The role of the user (either ADMIN or USER).")
    private UserRole role;

    /**
     * Indicates whether the user account is active. Default is true.
     */
    @Builder.Default
    @Schema(description = "Indicates whether the user account is active. Default is true.", defaultValue = "true")
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
     * The timestamp when the user record was created.
     */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @Schema(description = "The timestamp when the user record was created.")
    private Date createdDate;

    /**
     * The timestamp when the user record was last updated.
     */
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Schema(description = "The timestamp when the user record was last updated.")
    private Date updatedDate;
}
