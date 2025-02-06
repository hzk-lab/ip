package seedu.donk.task;

import seedu.donk.DonkException;

/**
 * Represents a generic task in the task list.
 * This is an abstract class that serves as the base for {@code ToDo}, {@code Deadline}, and {@code Event} tasks.
 */
public abstract class Task {
    private String name;
    private boolean status;

    /**
     * Constructs a {@code Task} with the given name and status.
     *
     * @param name   The name or description of the task.
     * @param status The completion status of the task.
     */
    public Task(String name, boolean status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return this.name;
    }

    public boolean getStatus() {
        return this.status;
    }

    /**
     * Marks the task as done.
     *
     * @throws DonkException If the task is already marked as done.
     */
    public void markAsDone() throws DonkException {
        if (this.status) {
            throw new DonkException("Oops!!! This task has already been done by you.");
        }
        this.status = true;
    }

    /**
     * Marks the task as not done.
     *
     * @throws DonkException If the task was already not completed.
     */
    public void markAsUndone() throws DonkException {
        if (!this.status) {
            throw new DonkException("Oops!!! This task has not been done before.");
        }
        this.status = false;
    }


    /**
     * Returns a string representation of the task,
     * showing whether it is completed or not.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        if (this.status) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }


    /**
     * Returns a formatted string representation of the task
     * for saving to a file.
     *
     * @return A string representation of the task formatted for file storage.
     */
    public abstract String toFileString();

}
