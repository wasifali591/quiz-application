package in.theexplorers.quiz.utilities;
/*
 * Copyright (c) 2024 TheExplorers.
 */

/**
 * Utility class for declaring constant string values used in the application.
 *
 * @author Md Wasif Ali
 * @version 1.0.0
 * @since 1.0.0
 */
public class StringConstants {
    // Records-related constants
    public static final String RECORDS_NOT_FOUND = "No records found!";
    public static final String RECORDS_EXISTS = "Records exists.";
    public static final String RECORDS_FETCHING = "Records fetching..";
    public static final String RECORDS_RETRIEVED = "Records retrieved. {}";

    public static final String RECORDS_TO_SAVE = "Records to save. {}";
    public static final String RECORDS_SAVED = "Records saved.";
    public static final String RECORDS_SAVING_FAILED = "Record saving failed!";

    public static final String RECORDS_TO_DELETE = "Records to delete. {}";
    public static final String RECORDS_DELETED = "Records deleted.";
    public static final String RECORDS_DELETION_FAILED = "Records deletion failed!";

    public static final String RECORDS_TO_UPDATE = "Records to update. {}";
    public static final String RECORDS_UPDATED = "Records updated.";
    public static final String RECORDS_UPDATING_FAILED = "Updating records failed!";

    public static final String ERROR_FETCHING_RECORDS = "Error during fetching records: {}";
    public static final String ERROR_SAVING_RECORDS = "Error while saving records: {}";
    public static final String ERROR_USER_SESSION = "User session error: {}";
    public static final String ERROR_DELETING_RECORDS = "Error while deleting records: {}";
    public static final String ERROR_UPDATING_RECORDS = "Error while updating records: {}";
    public static final String ERROR_UNKNOWN = "Something went wrong!";

    // Method-related constants
    public static final String METHOD_START = "{} method started.";
    public static final String METHOD_END = "{} method end.";

    // Debugging constants
    public static final String DEBUGGING_PROCESS = "Debugging Process. {}";

    // Operation
    public static final String OPERATION_PROCESSED_SUCCESSFULLY = "Operation processed successfully.";
    public static final String OPERATION_PARTIALLY_SUCCESSFUL = "Operation Partially processed successfully.";
    public static final String OPERATION_NOT_PERFORMED = "Operation not performed!";
    public static final String ERROR_PROCESSING_REQUEST = "Error processing request!";
    public static final String AUTHENTICATION_FAILED = "Authentication failed!";
    public static final String EXCEPTION_OCCURRED = "Exception occurred while performing operation! {}";

    private StringConstants() {
    }
}
