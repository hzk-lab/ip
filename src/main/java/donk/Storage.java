package donk;

import donk.task.Deadline;
import donk.task.Event;
import donk.task.Task;
import donk.task.ToDo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;

    public Storage(String file_path) {
        this.filePath = file_path;
    }

    // Load tasks from file (handling corruption)
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

    // Save tasks to file
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

    // Parses a task from a stored line in the file
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

    // Handle corrupted file by renaming it and starting fresh
    private void handleCorruptedFile(File corruptedFile) {
        File backupFile = new File("./ip/data/donk_corrupted.txt");

        if (corruptedFile.renameTo(backupFile)) {
            System.out.println("✅ Corrupted file has been renamed to donk_corrupted.txt.");
        } else {
            System.out.println("⚠️ Failed to rename corrupted file. Manual deletion required.");
        }
    }
}
