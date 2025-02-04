public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DonkException{
        throw new DonkException("Oops!!! You must declare the type of the task.");
    }
}
