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
            String userInput = scanner.nextLine().trim();

            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    int index = i + 1;
                    System.out.println(index + ". " + list.get(i).toString());
                }
            } else if (userInput.startsWith("mark ")) {
                int taskIdx = Integer.parseInt(userInput.substring(5)) - 1;

                if (taskIdx < 0 || taskIdx >= list.size()) {
                    System.out.println("Invalid task index.");
                }

                Task task = list.get(taskIdx);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + "        " + task.toString());
            } else if (userInput.startsWith("unmark ")) {
                int taskIdx = Integer.parseInt(userInput.substring(7)) - 1;

                if (taskIdx < 0 || taskIdx >= list.size()) {
                    System.out.println("Invalid task index.");
                }

                Task task = list.get(taskIdx);
                task.markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:\n" + "        " + task.toString());
            } else {
                Task task = new Task(userInput, false);
                list.add(task);
            }
        }
    }


}
