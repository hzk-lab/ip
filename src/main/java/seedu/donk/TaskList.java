package seedu.donk;

import seedu.donk.task.Deadline;
import seedu.donk.task.Event;
import seedu.donk.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The {@code TaskList} class manages a list of tasks and provides operations
 * to add, delete, mark, unmark, and search for tasks.
 */
public class TaskList {

    private final List<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} initialized with a given list of tasks.
     *
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:\n  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Deletes a task from the task list based on the given index.
     *
     * @param index The index of the task to delete (0-based).
     * @throws DonkException If the index is out of bounds.
     */
    public void deleteTask(int index) throws DonkException{
        Task task;
        try {
            task = tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DonkException("Oops!!! The task you just type in to delete doesn't exist.");
        }
        System.out.println("Noted. I've removed this task:\n  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Marks a task as done based on the given index.
     *
     * @param index The index of the task to mark as done (0-based).
     * @throws DonkException If the index is out of bounds.
     */
    public void markTask(int index) throws DonkException {
        Task task;
        try {
            task = tasks.get(index);
            task.markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DonkException("Oops!!! The task you just type in to mark as done doesn't exist.");
        }

        System.out.println("Nice! I've marked this task as done:\n" + "        " + task);
    }

    /**
     * Marks a task as not done based on the given index.
     *
     * @param index The index of the task to unmark (0-based).
     * @throws DonkException If the index is out of bounds.
     */
    public void unMarkTask(int index) throws DonkException {
        Task task;
        try {
            task = tasks.get(index);
            task.markAsUndone();
        } catch (IndexOutOfBoundsException e) {
            throw new DonkException("Oops!!! The task you just type in to mark as undone doesn't exist.");
        }

        System.out.println("OK, I've marked this task as not done yet:\n" + "        " + task);
    }


    /**
     * Prints all tasks in the task list.
     * If the list is empty, prints a message indicating so.
     */
    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty!");
        } else {
            System.out.println("Here are your tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }



    /**
     * Finds tasks occurring on a specific date and prints them.
     *
     * @param dateString The date string in {@code YYYY-MM-DD} format.
     */

    public void findDateTasks(String dateString) throws DonkException{
        try {
            LocalDate searchDate = LocalDate.parse(dateString);
            List<Task> results = findTasksByDate(tasks, searchDate);
            if (results.isEmpty()) {
                System.out.println("No tasks found on " + searchDate);
            } else {
                System.out.println("Here are the tasks on " + searchDate + ":");
                for (int i = 0; i < results.size(); i++) {
                    System.out.println((i + 1) + ". " + results.get(i));
                }
            }
        } catch (DateTimeParseException e) {
            throw new DonkException("Oops!!! Invalid date format! Use YYYY-MM-DD.");
        }
    }


    /**
     * Finds tasks occurring on a specific date and prints them.
     *
     * @param nameString The name String to search with.
     */
    public void findNameTasks(String nameString) {
        try {
            List<Task> results = findTasksByName(tasks, nameString);
            if (results.isEmpty()) {
                System.out.println("No tasks found with the word " + nameString);
            } else {
                System.out.println("Here are the tasks containing " + nameString + ":");
                for (int i = 0; i < results.size(); i++) {
                    System.out.println((i + 1) + ". " + results.get(i));
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Oops!!! Invalid date format! Use YYYY-MM-DD.");
        }
    }

    /**
     * Finds and returns a list of tasks that match the given date.
     *
     * @param tasks The list of tasks to search.
     * @param date The date to match against.
     * @return A list of tasks that occur on the given date.
     */
    public static List<Task> findTasksByDate(List<Task> tasks, LocalDate date) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            try {
                if (task instanceof Deadline && (LocalDate.parse(((Deadline) task).getBy()).equals(date))) {
                    matchingTasks.add(task);
                } else if (task instanceof Event && ((LocalDate.parse(((Event) task).getStart()).equals(date)) || (LocalDate.parse(((Event) task).getEnd()).equals(date)))) {
                    matchingTasks.add(task);
                }
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        return matchingTasks;
    }


    /**
     * Finds and returns a list of tasks that match the given name.
     *
     * @param tasks The list of tasks to search.
     * @param nameString The name to match against.
     * @return A list of tasks with the given name.
     */
    public static List<Task> findTasksByName(List<Task> tasks, String nameString) {
        List<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getName().toLowerCase().contains(nameString.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }

}

