package donk.command;

import donk.DonkException;
import donk.Storage;
import donk.TaskList;
import donk.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DonkException;
    public boolean isExit() {
        return false;
    }
}


