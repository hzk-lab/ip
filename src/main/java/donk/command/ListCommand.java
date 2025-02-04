package donk.command;

import donk.Storage;
import donk.TaskList;
import donk.Ui;
import donk.command.Command;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printTasks();
    }
}
