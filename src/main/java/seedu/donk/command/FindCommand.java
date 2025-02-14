package seedu.donk.command;

import seedu.donk.DonkException;
import seedu.donk.Storage;
import seedu.donk.TaskList;
import seedu.donk.Ui;

public abstract class FindCommand extends Command {
    protected String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DonkException;
}