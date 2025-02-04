package donk.command;

import donk.DonkException;
import donk.Storage;
import donk.TaskList;
import donk.Ui;
import donk.command.Command;

public class FindCommand extends Command {
    private String dateString;

    public FindCommand(String dateString) {
        this.dateString = dateString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DonkException {
        tasks.findTasks(dateString);
    }
}
