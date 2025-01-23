import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Donk {
    public static void main(String[] args) {
        String logo = "____________________________________________________________\n"
                + "Hello! I'm Donk\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        System.out.println("Hello from\n" + logo);

        List<Task> list = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter your message: ");
            // String userInput = scanner.nextLine().trim();
            String firstWord = scanner.next();
            if (firstWord.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (firstWord.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    int index = i + 1;
                    System.out.println(index + ". " + list.get(i).toString());
                }
            } else if (firstWord.equals("mark")) {
                int taskIdx = scanner.nextInt() - 1;

                if (taskIdx < 0 || taskIdx >= list.size()) {
                    System.out.println("Invalid task index.");
                }

                Task task = list.get(taskIdx);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + "        " + task.toString());
            } else if (firstWord.equals("unmark")) {
                int taskIdx = scanner.nextInt() - 1;

                if (taskIdx < 0 || taskIdx >= list.size()) {
                    System.out.println("Invalid task index.");
                }

                Task task = list.get(taskIdx);
                task.markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:\n" + "        " + task.toString());
            } else if (firstWord.equals("todo")) {
                String name = scanner.nextLine().trim();
                ToDo task = new ToDo(name);
                list.add(task);
                System.out.println("Got it. I've added this task:\n" + "        "
                                        + task.toString() + "\n"
                                        + "Now you have " + list.size() + " tasks in the list.");

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

                Deadline task = new Deadline(name, date);
                list.add(task);
                System.out.println("Got it. I've added this task:\n" + "        "
                        + task.toString() + "\n"
                        + "Now you have " + list.size() + " tasks in the list.");

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

                Event task = new Event(name, start, end);
                list.add(task);
                System.out.println("Got it. I've added this task:\n" + "        "
                        + task.toString() + "\n"
                        + "Now you have " + list.size() + " tasks in the list.");
            } else {
                Task task = new Task(scanner.nextLine(), false);
                list.add(task);
            }
        }

    }


}
