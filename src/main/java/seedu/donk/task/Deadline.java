package seedu.donk.task;

import seedu.donk.DonkException;
import seedu.donk.ParseDate;

/**
 * Represents a deadline task in the task list.
 * A {@code Deadline} task has a due date and extends the {@code Task} class.
 */
public class Deadline extends Task {

    protected String by;
    private String processedBy;

    /**
     * Constructs a {@code Deadline} task with the given name, due date, and status.
     *
     * @param name   The name of the task.
     * @param by     The due date of the task (as a string).
     * @param status The completion status of the task.
     * @throws DonkException If the task name or due date is empty or null.
     */
    public Deadline(String name, String by, boolean status) throws DonkException {
        super(name, status);
        this.by = by;
        if (name == null || name.trim().isEmpty())
            throw new DonkException("Oops!!! You must type in the description of the Deadline task.");
        if (by == null || by.trim().isEmpty())
            throw new DonkException("Oops!!! Your Deadline task must have a due time.");

        processedBy = ParseDate.parseDateOrReturnOriginal(by);
    }

    /**
     * Returns a string representation of the deadline task,
     * including its status and formatted due date.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + processedBy + ")";
    }

    /**
     * Returns a formatted string representation of the deadline task
     * for saving to a file.
     *
     * @return A string representation of the task formatted for file storage.
     */
    @Override
    public String toFileString() {
        return "D | " + (getStatus() ? "1" : "0") + " | " + getName() + " | " + this.by;
    }

    public String getBy() {
        return this.by;
    }


}
