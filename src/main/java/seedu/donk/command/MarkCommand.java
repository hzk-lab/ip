package seedu.donk.command;

import seedu.donk.DonkException;
import seedu.donk.Storage;
import seedu.donk.TaskList;
import seedu.donk.Ui;

public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DonkException {
        String result = tasks.markTask(taskIndex - 1);
        storage.saveTasks(tasks.getTasks());
        return result;
    }
}
