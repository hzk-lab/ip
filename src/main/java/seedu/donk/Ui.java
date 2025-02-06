package seedu.donk;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println("____________________________________________________________\n"
                + "Hello! I'm Donk\n"
                + "What can I do for you?\n");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file.");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void close() {
        scanner.close();
    }
}

