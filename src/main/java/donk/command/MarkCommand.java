package donk.command;

import donk.DonkException;
import donk.Storage;
import donk.TaskList;
import donk.Ui;
import donk.command.Command;

public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DonkException {
        tasks.markTask(taskIndex - 1);
        storage.saveTasks(tasks.getTasks());
    }
}
