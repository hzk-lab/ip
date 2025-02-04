package donk.command;

import donk.DonkException;
import donk.Storage;
import donk.TaskList;
import donk.Ui;
import donk.command.Command;

public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DonkException {
        tasks.deleteTask(taskIndex);
        storage.saveTasks(tasks.getTasks());
    }
}
