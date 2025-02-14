package seedu.donk.command;

import seedu.donk.DonkException;
import seedu.donk.Storage;
import seedu.donk.TaskList;
import seedu.donk.Ui;

public class InvalidCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DonkException {
        throw new DonkException("Oops!!! You must declare the type of the task.");
    }
}
