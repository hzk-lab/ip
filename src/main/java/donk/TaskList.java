package donk;

import donk.task.Deadline;
import donk.task.Event;
import donk.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:\n  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

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

    public void findTasks(String dateString) {
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
            System.out.println("Oops!!! Invalid date format! Use YYYY-MM-DD.");
        }
    }

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

}

