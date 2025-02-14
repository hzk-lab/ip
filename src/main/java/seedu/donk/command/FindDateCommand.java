package seedu.donk.command;

import seedu.donk.DonkException;
import seedu.donk.Storage;
import seedu.donk.TaskList;
import seedu.donk.Ui;

public class FindDateCommand extends FindCommand {

    public FindDateCommand(String name) {
        super(name);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DonkException {
        return tasks.findDateTasks(super.searchString);
    }
}
