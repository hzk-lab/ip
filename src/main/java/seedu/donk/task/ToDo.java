package seedu.donk.task;

import seedu.donk.DonkException;

/**
 * Represents a To-Do task in the task list.
 * A {@code ToDo} task is a simple task without a specific date or time.
 */
public class ToDo extends Task {

    /**
     * Constructs a {@code ToDo} task with the given name and status.
     *
     * @param name   The name of the task.
     * @param status The completion status of the task.
     * @throws DonkException If the task name is empty or null.
     */
    public ToDo(String name, boolean status) throws DonkException {
        super(name, status);
        if (name == null || name.trim().isEmpty()) {
            throw new DonkException("Oops!!! You must type in the description of the ToDo task.");
        }
    }

    /**
     * Returns a string representation of the To-Do task,
     * including its status.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a formatted string representation of the To-Do task
     * for saving to a file.
     *
     * @return A string representation of the task formatted for file storage.
     */
    @Override
    public String toFileString() {
        return "T | " + (getStatus() ? "1" : "0") + " | " + getName();
    }

}
