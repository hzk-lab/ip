import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Donk {
    public static void main(String[] args) {
        String logo = "____________________________________________________________\n"
                + "Hello! I'm Donk\n"
                + "What can I do for you?\n";
        System.out.println(logo);

        List<Task> list = Storage.loadTasks();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter your message:");
            String firstWord = scanner.next();

            if (firstWord.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (firstWord.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    int index = i + 1;
                    System.out.println(index + ". " + list.get(i).toString());
                }
            } else if (firstWord.equals("mark")) {

                try {
                    int taskIdx = scanner.nextInt() - 1;
                    Task task = list.get(taskIdx);
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + "        " + task.toString());
                    Storage.saveTasks(list);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Oops!!! The task you just type in to mark as done doesn't exist.");
                } catch (DonkException e) {
                    System.out.println(e.toString());
                }

            } else if (firstWord.equals("unmark")) {

                try {
                    int taskIdx = scanner.nextInt() - 1;
                    Task task = list.get(taskIdx);
                    task.markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:\n" + "        " + task.toString());
                    Storage.saveTasks(list);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Oops!!! The task you just type in to mark as undone doesn't exist.");
                } catch (DonkException e) {
                    System.out.println(e.toString());
                }


            } else if (firstWord.equals("todo")) {

                try {
                    String name = scanner.nextLine().trim();
                    ToDo task = new ToDo(name, false);
                    list.add(task);
                    System.out.println("Got it. I've added this task:\n" + "        "
                            + task.toString() + "\n"
                            + "Now you have " + list.size() + " tasks in the list.");
                    Storage.saveTasks(list);
                } catch (DonkException e) {
                    System.out.println(e.toString());
                }

            } else if (firstWord.equals("deadline")) {
                String text = scanner.nextLine();
                String[] words = text.split("\\s+");

                StringBuilder beforeSlash = new StringBuilder();
                StringBuilder afterSlash = new StringBuilder();

                boolean foundSlash = false;

                for (String word : words) {
                    if (word.equals("/by") && !foundSlash) {
                        foundSlash = true;
                    } else if (foundSlash) {
                        afterSlash.append(word).append(" ");
                    } else {
                        beforeSlash.append(word).append(" ");
                    }
                }

                // Remove trailing space if any
                String name = beforeSlash.toString().trim();
                String date = afterSlash.toString().trim();

                try {
                    Deadline task = new Deadline(name, date, false);
                    list.add(task);
                    System.out.println("Got it. I've added this task:\n" + "        "
                            + task.toString() + "\n"
                            + "Now you have " + list.size() + " tasks in the list.");
                    Storage.saveTasks(list);
                } catch (DonkException e) {
                    System.out.println(e.toString());
                }

            } else if (firstWord.equals("event")) {
                String text = scanner.nextLine();
                String[] words = text.split("\\s+");

                StringBuilder beforeFirstSlash = new StringBuilder();
                StringBuilder betweenSlashes = new StringBuilder();
                StringBuilder afterSecondSlash = new StringBuilder();

                int slashCount = 0;

                for (String word : words) {
                    if (word.startsWith("/")) {
                        slashCount++;
                    } else if (slashCount == 0) {
                        beforeFirstSlash.append(word).append(" ");
                    } else if (slashCount == 1) {
                        betweenSlashes.append(word).append(" ");
                    } else {
                        afterSecondSlash.append(word).append(" ");
                    }
                }

                // Remove trailing spaces if any
                String name = beforeFirstSlash.toString().trim();
                String start = betweenSlashes.toString().trim();
                String end = afterSecondSlash.toString().trim();

                try {
                    Event task = new Event(name, start, end, false);
                    list.add(task);
                    System.out.println("Got it. I've added this task:\n" + "        "
                            + task.toString() + "\n"
                            + "Now you have " + list.size() + " tasks in the list.");
                    Storage.saveTasks(list);
                } catch (DonkException e) {
                    System.out.println(e.toString());
                }
            } else if (firstWord.equals("delete")) {

                try {
                    int taskIdx = scanner.nextInt() - 1;
                    Task task = list.get(taskIdx);
                    list.remove(taskIdx);
                    System.out.println("Noted. I've removed this task:\n" + "        " + task.toString()
                                        + "\n" + "Now you have " + list.size() + " tasks in the list.");
                    Storage.saveTasks(list);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Oops!!! The task you just type in to delete doesn't exist.");
                }

            } else if (firstWord.equals("find")) {

                String dateString = scanner.next();
                try {
                    LocalDate searchDate = LocalDate.parse(dateString);
                    List<Task> results = findTasksByDate(list, searchDate);
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

            } else {
                System.out.println("Oops!!! You must declare the type of the task.");
                scanner.nextLine();
            }
        }

        scanner.close();

    }

    public static List<Task> findTasksByDate(List<Task> tasks, LocalDate date) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            try {
                if (task instanceof Deadline && (LocalDate.parse(((Deadline) task).by).equals(date))) {
                    matchingTasks.add(task);
                } else if (task instanceof Event && ((LocalDate.parse(((Event) task).start).equals(date)) || (LocalDate.parse(((Event) task).end).equals(date)))) {
                    matchingTasks.add(task);
                }
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        return matchingTasks;
    }

}
