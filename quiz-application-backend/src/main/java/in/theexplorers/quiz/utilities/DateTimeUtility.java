package in.theexplorers.quiz.utilities;
/*
 * Copyright (c) 2024 TheExplorers.
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for common date and time operations.
 *
 * @author Wasif
 * @version 1.0.0
 * @since 1.0.0
 */
public class DateTimeUtility {

    /**
     * Returns the current timestamp formatted as "yy-MM-dd HH-mm-ss".
     *
     * @return A string representing the current timestamp in the format "yy-MM-dd HH-mm-ss".
     */
    public static String getCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH-mm-ss");
        return LocalDateTime.now().format(formatter);
    }
}

