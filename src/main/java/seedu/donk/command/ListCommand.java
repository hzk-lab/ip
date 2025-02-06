package seedu.donk.command;

import seedu.donk.Storage;
import seedu.donk.TaskList;
import seedu.donk.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printTasks();
    }
}
