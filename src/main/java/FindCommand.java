public class FindCommand extends Command{
    private String dateString;

    public FindCommand(String dateString) {
        this.dateString = dateString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DonkException {
        tasks.findTasks(dateString);
    }
}
