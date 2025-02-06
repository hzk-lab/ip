package seedu.donk;

import org.junit.jupiter.api.Test;
import seedu.donk.task.ToDo;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void constructor_validName_success() throws DonkException {
        ToDo todo = new ToDo("Finish homework", false);
        assertEquals("Finish homework", todo.getName());
        assertFalse(todo.getStatus());
    }

    @Test
    void constructor_emptyName_throwsDonkException() {
        assertThrows(DonkException.class, () -> new ToDo("", false));
        assertThrows(DonkException.class, () -> new ToDo("   ", false));
        assertThrows(DonkException.class, () -> new ToDo(null, false));
    }

    @Test
    void toString_correctFormat() throws DonkException {
        ToDo todo = new ToDo("Read book", false);
        assertEquals("[T][ ] Read book", todo.toString());

        todo.markAsDone();
        assertEquals("[T][X] Read book", todo.toString());
    }

    @Test
    void toFileString_correctFormat() throws DonkException {
        ToDo todo = new ToDo("Buy groceries", false);
        assertEquals("T | 0 | Buy groceries", todo.toFileString());

        todo.markAsDone();
        assertEquals("T | 1 | Buy groceries", todo.toFileString());
    }
}
