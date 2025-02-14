package seedu.donk.command;

import seedu.donk.DonkException;
import seedu.donk.Storage;
import seedu.donk.TaskList;
import seedu.donk.Ui;

public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DonkException;
    public boolean isExit() {
        return false;
    }
}


