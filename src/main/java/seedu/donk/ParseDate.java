package seedu.donk;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A utility class for parsing and formatting dates.
 * It provides methods for converting date strings into a standard format
 * and validating the chronological order of start and end dates.
 */
public class ParseDate {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * Parses a date string into a standard format (e.g., "MMM d yyyy").
     * If the input string is not in a valid date format, it is returned as-is.
     *
     * @param by The date string to be parsed.
     * @return The formatted date string if parsing is successful, or the original input if parsing fails.
     */
    public static String parseDateOrReturnOriginal(String by) {
        try {
            assert by != null : "Date string should not be null";
            LocalDate date = LocalDate.parse(by);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            return by;
        }
    }


    /**
     * Checks whether the start date is before the end date.
     *
     * @param start The start date string.
     * @param end   The end date string.
     * @return {@code true} if the start date is before the end date, or if parsing fails.
     */
    public static boolean judgeStartAndEnd(String start, String end) {
        try {
            LocalDate dateStart = LocalDate.parse(start);
            LocalDate dateEnd = LocalDate.parse(end);
            return dateStart.isBefore(dateEnd);
        } catch (DateTimeParseException e) {
            return true; // Pass the judgement to the next method if the date format is incorrect
        }
    }

}
