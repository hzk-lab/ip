import java.util.Scanner;

public class Donk {
    public static void main(String[] args) {
        String logo = "____________________________________________________________\n"
                + "Hello! I'm Donk\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter your message: ");
            String userInput = scanner.nextLine().trim();

            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(userInput);
            }
        }
    }


}
