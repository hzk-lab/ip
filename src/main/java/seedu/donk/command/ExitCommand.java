package seedu.donk.command;

import seedu.donk.Storage;
import seedu.donk.TaskList;
import seedu.donk.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
