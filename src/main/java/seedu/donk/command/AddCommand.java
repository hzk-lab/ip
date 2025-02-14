package seedu.donk.command;

import seedu.donk.DonkException;
import seedu.donk.task.Task;
import seedu.donk.Storage;
import seedu.donk.TaskList;
import seedu.donk.Ui;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DonkException {
        storage.saveTasks(tasks.getTasks());
        return tasks.addTask(task);
    }
}
