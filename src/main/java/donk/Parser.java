package donk;

import donk.command.*;
import donk.task.Deadline;
import donk.task.Event;
import donk.task.Task;
import donk.task.ToDo;

public class Parser {
    public static Command parseCommand(String input) throws DonkException {
        String[] words = input.split(" ", 2);
        String command = words[0].toLowerCase();


        switch (command) {
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            case "mark":
                try {
                    return new MarkCommand(Integer.parseInt(words[1]));
                } catch (NumberFormatException e) {
                    throw new DonkException("Oops!!! Invalid number: " + words[1]
                            + ". Please enter a valid number to mark.");
                }
            case "unmark":
                try {
                    return new UnmarkCommand(Integer.parseInt(words[1]));
                } catch (NumberFormatException e) {
                    throw new DonkException("Oops!!! Invalid number: " + words[1]
                            + ". Please enter a valid number to unmark.");
                }
            case "delete":
                try {
                    return new DeleteCommand(Integer.parseInt(words[1]));
                } catch (NumberFormatException e) {
                    throw new DonkException("Oops!!! Invalid input: " + words[1]
                            + ". Please enter a valid number to delete.");
                }
            case "todo":
                return new AddCommand(new ToDo(words[1], false));
            case "deadline":
                String[] deadlineParts = words[1].split(" /by ");
                if (deadlineParts.length < 2) {
                    throw new DonkException("Invalid deadline format! Use: deadline <task> /by <date>");
                }
                return new AddCommand(new Deadline(deadlineParts[0], deadlineParts[1], false));
            case "event":
                String[] eventParts = words[1].split(" /from | /to ");
                if (eventParts.length < 3) {
                    throw new DonkException("Invalid event format! Use: event <task> /from <start> /to <end>");
                }
                return new AddCommand(new Event(eventParts[0], eventParts[1], eventParts[2], false));
            case "find":
                return new FindCommand(words[1]);
            default:
                return new InvalidCommand();
        }
    }

    public static Task parseTask(String line) throws DonkException{
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                return new ToDo(description, isDone);
            case "D":
                return new Deadline(description, parts[3], isDone);
            case "E":
                return new Event(description, parts[3], parts[4], isDone);
            default:
                throw new IllegalArgumentException("Unknown task type.");
        }
    }
}
