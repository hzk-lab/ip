package seedu.donk;

import seedu.donk.command.Command;

/**
 * The main class for the Donk chatbot application.
 * It initializes the necessary components and runs the main command loop.
 */
public class Donk {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Donk chatbot instance.
     * Initializes UI, Storage, and TaskList by loading tasks from a file.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Donk(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList tempTasks;

        tempTasks = new TaskList(storage.loadTasks());
        this.tasks = tempTasks;
    }

    /**
     * Runs the chatbot by continuously reading user input and executing commands.
     * The loop terminates when an exit command is issued.
     */
    public void run() {
        ui.showWelcome();
        while (true) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parseCommand(input);
                command.execute(tasks, ui, storage);
                if (command.isExit()) {
                    break;
                }
            } catch (DonkException e) {
                ui.showMessage(e.getMessage());
            }
        }
        ui.close();
    }

    /**
     * The main entry point of the application.
     * Creates a new Donk instance and starts the chatbot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Donk("./data/donk.txt").run();
    }
}

