package seedu.donk;

import seedu.donk.task.Deadline;
import seedu.donk.task.Event;
import seedu.donk.task.Task;
import seedu.donk.task.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Storage} class is responsible for handling file operations related to saving and loading tasks.
 * It ensures that tasks persist across application runs by storing them in a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a {@code Storage} instance with a specified file path.
     *
     * @param file_path The file path where tasks are stored.
     */
    public Storage(String file_path) {
        this.filePath = file_path;
    }

    /**
     * Loads tasks from the specified file.
     * If the file does not exist, a new task list is created.
     * If the file is corrupted, it is renamed, and an empty task list is returned.
     *
     * @return A list of {@code Task} objects loaded from the file.
     */
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(this.filePath);

        if (!file.exists()) {
            System.out.println("No previous tasks found. Creating a new task list.");
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                } else {
                    throw new IOException("Corrupted file detected at line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("⚠️ Warning: Data file is corrupted! Resetting...");
            handleCorruptedFile(file);
            return new ArrayList<>(); // Return an empty task list
        }

        return tasks;
    }

    /**
     * Saves the given list of tasks to the file.
     * Ensures that the directory structure exists before writing.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasks(List<Task> tasks) {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // Ensure directory exists

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Parses a task from a stored line in the file.
     *
     * @param line The task entry in the file.
     * @return The corresponding {@code Task} object, or {@code null} if the line is corrupted.
     */
    private Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) return null; // Ensure correct format
            // System.out.println("x");
            String type = parts[0].trim();
            boolean isDone = parts[1].trim().equals("1");
            String name = parts[2].trim();

            switch (type) {
                case "T":
                    return new ToDo(name, isDone);
                case "D":
                    return new Deadline(name, parts[3].trim(), isDone);
                case "E":
                    return new Event(name, parts[3].trim(), parts[4].trim(), isDone);
                default:
                    System.out.println("y");
                    return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("z");
            return null; // Return null to indicate a corrupted line
        }
    }

    /**
     * Handles a corrupted file by renaming it and starting fresh.
     * The corrupted file is renamed to {@code donk_corrupted.txt}.
     *
     * @param corruptedFile The corrupted file to be renamed.
     */
    private void handleCorruptedFile(File corruptedFile) {
        File backupFile = new File("./ip/data/donk_corrupted.txt");

        if (corruptedFile.renameTo(backupFile)) {
            System.out.println("✅ Corrupted file has been renamed to donk_corrupted.txt.");
        } else {
            System.out.println("⚠️ Failed to rename corrupted file. Manual deletion required.");
        }
    }
}
