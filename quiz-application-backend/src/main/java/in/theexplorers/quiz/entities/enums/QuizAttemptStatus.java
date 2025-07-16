package in.theexplorers.quiz.entities.enums;
/*
 * Copyright (c) 2024 TheExplorers.
 */

/**
 * Enum representing the status of the quiz in the application.
 *
 * <p>
 * This enum defines three status:
 * </p>
 * <ul>
 *   <li><b>NOT_STARTED</b>: Represents quiz is not started yet.</li>
 *   <li><b>STARTED</b>: Represents quiz already started.</li>
 *   <li><b>COMPLETED</b>: Represents quiz is completed.</li>
 * </ul>
 *
 * @author Md Wasif Ali
 * @version 1.0.0
 * @since 1.0.0
 */
public enum QuizAttemptStatus {
    /**
     * Represents quiz is not started yet.
     */
    NOT_STARTED,
    /**
     * Represents quiz already started.
     */
    STARTED,
    /**
     * Represents quiz is completed.
     */
    COMPLETED
}
