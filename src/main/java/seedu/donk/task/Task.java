package seedu.donk.task;

import seedu.donk.DonkException;

public abstract class Task {
    private String name;
    private boolean status;

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

    public void markAsDone() throws DonkException {
        if (this.status) {
            throw new DonkException("Oops!!! This task has already been done by you.");
        }
        this.status = true;
    }

    public void markAsUndone() throws DonkException {
        if (!this.status) {
            throw new DonkException("Oops!!! This task has not been done before.");
        }
        this.status = false;
    }

    @Override
    public String toString() {
        if (this.status) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }


    public abstract String toFileString();



}
