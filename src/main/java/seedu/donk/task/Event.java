package seedu.donk.task;

import seedu.donk.DonkException;
import seedu.donk.ParseDate;

import java.time.LocalDate;


/**
 * Represents an event task in the task list.
 * An {@code Event} task has a start time and an end time and extends the {@code Task} class.
 */
public class Event extends Task {
    private String start;
    private String end;

    private String processedStart;
    private String processedEnd;


    /**
     * Constructs an {@code Event} task with the given name, start time, end time, and status.
     *
     * @param name   The name of the task.
     * @param start  The start time of the event (as a string).
     * @param end    The end time of the event (as a string).
     * @param status The completion status of the task.
     * @throws DonkException If the task name, start time, or end time is empty or null.
     *                       Also throws an exception if the end time is earlier than the start time.
     */
    public Event(String name, String start, String end, boolean status) throws DonkException {
        super(name, status);
        this.start = start;
        this.end = end;

        if (name == null || name.trim().isEmpty())
            throw new DonkException("Oops!!! You must type in the description of the Event task.");
        if (start == null || start.trim().isEmpty())
            throw new DonkException("Oops!!! Your Event task must have a start time.");
        if (end == null || end.trim().isEmpty())
            throw new DonkException("Oops!!! Your Event task must have a end time.");

        if (!ParseDate.judgeStartAndEnd(start, end)) {
            throw new DonkException("Oops!!! Your event end time is earlier than its start time.");
        }

        processedStart = ParseDate.parseDateOrReturnOriginal(start);
        processedEnd = ParseDate.parseDateOrReturnOriginal(end);
    }

    /**
     * Returns a string representation of the event task,
     * including its status, start time, and end time.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        LocalDate d1 = LocalDate.parse(start);
        LocalDate d2 = LocalDate.parse(end);

        return "[E]" + super.toString() + " (from: " + processedStart + " to: " + processedEnd + ")";
    }

    /**
     * Returns a formatted string representation of the event task
     * for saving to a file.
     *
     * @return A string representation of the task formatted for file storage.
     */
    @Override
    public String toFileString() {
        return "E | " + (getStatus() ? "1" : "0") + " | " + getName() + " | " + start + " | " + end;
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

}
