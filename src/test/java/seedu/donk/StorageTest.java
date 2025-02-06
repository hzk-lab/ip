package seedu.donk;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.donk.task.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {
    private static final String TEST_FILE_PATH = "./test_data/donk_test.txt";
    private static final String CORRUPTED_FILE_PATH = "./test_data/donk_corrupted.txt";
    private Storage storage;

    @BeforeEach
    void setUp() {
        storage = new Storage(TEST_FILE_PATH);
        new File(TEST_FILE_PATH).getParentFile().mkdirs(); // Ensure directory exists
    }

    @AfterEach
    void tearDown() {
        // Clean up test files after each test
        new File(TEST_FILE_PATH).delete();
        new File(CORRUPTED_FILE_PATH).delete();
    }

    @Test
    void loadTasks_noFile_returnsEmptyList() {
        List<Task> tasks = storage.loadTasks();
        assertTrue(tasks.isEmpty(), "Expected an empty task list when no file exists.");
    }

    @Test
    void saveTasks_thenLoadTasks_success() throws DonkException{
        Task todo = new ToDo("Buy groceries", false);
        Task deadline = new Deadline("Submit assignment", "2025-02-10", false);
        Task event = new Event("Meeting", "2025-02-10", "2025-02-12", false);
        List<Task> tasks = List.of(todo, deadline, event);

        storage.saveTasks(tasks);
        List<Task> loadedTasks = storage.loadTasks();

        assertEquals(3, loadedTasks.size(), "Expected 3 tasks to be loaded.");
        assertEquals("Buy groceries", loadedTasks.get(0).getName());
        assertEquals("Submit assignment", loadedTasks.get(1).getName());
        assertEquals("Meeting", loadedTasks.get(2).getName());
    }

    @Test
    void loadTasks_corruptedFile_handlesGracefully() throws IOException {
        // Create a corrupted file
        Files.write(Paths.get(CORRUPTED_FILE_PATH), "T | 1\nD | X | Incorrect Format".getBytes());

        Storage corruptedStorage = new Storage(CORRUPTED_FILE_PATH);
        List<Task> tasks = corruptedStorage.loadTasks();

        assertTrue(tasks.isEmpty(), "Expected empty list due to corruption.");
        assertTrue(new File(CORRUPTED_FILE_PATH).exists(), "Corrupted file should be renamed.");
    }

    @Test
    void saveTasks_createsFileIfNotExists() throws DonkException{
        storage.saveTasks(List.of(new ToDo("Test Task", false)));
        assertTrue(new File(TEST_FILE_PATH).exists(), "File should be created after saving.");
    }
}
