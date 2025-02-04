package donk.command;

import donk.DonkException;
import donk.Storage;
import donk.TaskList;
import donk.Ui;
import donk.command.Command;

public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DonkException {
        throw new DonkException("Oops!!! You must declare the type of the task.");
    }
}
