package seedu.donk.command;

import seedu.donk.Storage;
import seedu.donk.TaskList;
import seedu.donk.Ui;

/**
 * Represents a command that sorts tasks by time.
 */
public class SortCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.sortTasksByTime();
    }

}
