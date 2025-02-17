package seedu.donk.command;

import seedu.donk.DonkException;
import seedu.donk.Storage;
import seedu.donk.TaskList;
import seedu.donk.Ui;

public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DonkException {
        storage.saveTasks(tasks.getTasks());
        storage.saveTasks(tasks.getTasks());
        return tasks.deleteTask(taskIndex - 1);
    }
}
