package in.theexplorers.quiz.entities.enums;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum representing the roles of users in the quiz application.
 *
 * <p>
 * This enum defines two roles:
 * </p>
 * <ul>
 *   <li><b>ADMIN</b>: Represents an admin user who has privileges to manage quizzes and user data.</li>
 *   <li><b>USER</b>: Represents a regular user who participates in quizzes.</li>
 * </ul>
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
@Schema(description = "Represents the roles of users in the quiz application.")
public enum UserRole {
    /**
     * Represents an admin user with management privileges.
     */
    @Schema(description = "Represents an admin user with management privileges.")
    ADMIN,

    /**
     * Represents a regular user participating in quizzes.
     */
    @Schema(description = "Represents a regular user participating in quizzes.")
    USER
}
