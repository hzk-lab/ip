package seedu.donk;

import seedu.donk.command.*;
import seedu.donk.task.Deadline;
import seedu.donk.task.Event;
import seedu.donk.task.Task;
import seedu.donk.task.ToDo;

/**
 * The {@code Parser} class is responsible for interpreting user input
 * and converting it into executable commands for the Donk chatbot.
 */
public class Parser {
    /**
     * Parses a given user input and returns the corresponding {@code Command}.
     *
     * @param input The user input string.
     * @return The parsed {@code Command} object.
     * @throws DonkException If the input format is invalid.
     */
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
                if (words.length < 2) {
                    throw new DonkException("Invalid Todo format! You must type in the description.");
                }
                return new AddCommand(new ToDo(words[1], false));
            case "deadline":
                String[] deadlineParts = words[1].split(" /by ");
                if (deadlineParts.length < 2) {
                    throw new DonkException("Invalid Deadline format! Use: deadline <task> /by <date>");
                }
                return new AddCommand(new Deadline(deadlineParts[0], deadlineParts[1], false));
            case "event":
                String[] eventParts = words[1].split(" /from | /to ");
                if (eventParts.length < 3) {
                    throw new DonkException("Invalid Event format! Use: event <task> /from <start> /to <end>");
                }
                return new AddCommand(new Event(eventParts[0], eventParts[1], eventParts[2], false));
            case "get":
                if (words.length < 2) {
                    throw new DonkException("Invalid Get format! You must type in the date of the task.");
                }
                return new FindDateCommand(words[1]);
            case "find":
                if (words.length < 2) {
                    throw new DonkException("Invalid Find format! You must type in the name of the task.");
                }
                return new FindNameCommand(words[1]);
            default:
                return new InvalidCommand();
        }
    }

    /**
     * Parses a stored task entry from a file into a {@code Task} object.
     *
     * @param line The task entry in the stored file.
     * @return The corresponding {@code Task} object.
     * @throws DonkException If the format of the task entry is invalid.
     */
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
