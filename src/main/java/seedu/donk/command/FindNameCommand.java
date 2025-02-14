package seedu.donk.command;

import seedu.donk.DonkException;
import seedu.donk.Storage;
import seedu.donk.TaskList;
import seedu.donk.Ui;

public class FindNameCommand extends FindCommand {

    public FindNameCommand(String name) {
        super(name);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DonkException {
        return tasks.findNameTasks(super.searchString);
    }
}
