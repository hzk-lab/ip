package seedu.donk.task;

import seedu.donk.DonkException;

public class ToDo extends Task {

    public ToDo(String name, boolean status) throws DonkException {
        super(name, status);
        if (name == null || name.trim().isEmpty()) {
            throw new DonkException("Oops!!! You must type in the description of the ToDo task.");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + (getStatus() ? "1" : "0") + " | " + getName();
    }

}
