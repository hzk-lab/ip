package seedu.donk;  //same package as the class being tested
import seedu.donk.task.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    void addTask_taskIsAdded_success() throws DonkException{
        Task todo = new ToDo("Finish report", false);
        taskList.addTask(todo);
        assertEquals(1, taskList.getTasks().size());
        assertEquals(todo, taskList.getTasks().get(0));
    }

    @Test
    void deleteTask_taskIsRemoved_success() throws DonkException{
        Task todo = new ToDo("Finish report", false);
        taskList.addTask(todo);
        taskList.deleteTask(0);
        assertEquals(0, taskList.getTasks().size());
    }

    @Test
    void markTask_taskIsMarked_success() throws DonkException{
        Task todo = new ToDo("Finish report", false);
        taskList.addTask(todo);
        taskList.markTask(0);
        assertTrue(todo.getStatus());
    }

    @Test
    void unmarkTask_taskIsUnmarked_success() throws DonkException{
        Task todo = new ToDo("Finish report", false);
        taskList.addTask(todo);
        taskList.markTask(0);
        taskList.unMarkTask(0);
        assertFalse(todo.getStatus());
    }

    @Test
    void findTasksByDate_correctTasksReturned() throws DonkException {
        Task deadline = new Deadline("Submit assignment", "2025-02-10", false);
        Task event = new Event("Company Meeting", "2025-02-10", "2025-02-12", false);

        taskList.addTask(deadline);
        taskList.addTask(event);

        List<Task> results = taskList.findTasksByDate(taskList.getTasks(), LocalDate.of(2025, 2, 10));

        assertEquals(2, results.size());
        assertTrue(results.contains(deadline));
        assertTrue(results.contains(event));
    }

    @Test
    void findTasksByDate_noMatchingTasks_returnsEmptyList() throws DonkException {
        Task deadline = new Deadline("Submit assignment", "2025-02-10", false);
        taskList.addTask(deadline);

        List<Task> results = taskList.findTasksByDate(taskList.getTasks(), LocalDate.of(2025, 3, 1));

        assertEquals(0, results.size());
    }
}