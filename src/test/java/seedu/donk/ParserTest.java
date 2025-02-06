package seedu.donk;
import org.junit.jupiter.api.Test;
import seedu.donk.Parser;
import seedu.donk.command.*;
import seedu.donk.task.*;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parseCommand_validCommands_returnCorrectCommand() throws DonkException {
        assertInstanceOf(ListCommand.class, Parser.parseCommand("list"));
        assertInstanceOf(ExitCommand.class, Parser.parseCommand("bye"));
        assertInstanceOf(MarkCommand.class, Parser.parseCommand("mark 1"));
        assertInstanceOf(UnmarkCommand.class, Parser.parseCommand("unmark 1"));
        assertInstanceOf(DeleteCommand.class, Parser.parseCommand("delete 1"));
        assertInstanceOf(AddCommand.class, Parser.parseCommand("todo Read book"));
        assertInstanceOf(AddCommand.class, Parser.parseCommand("deadline Submit report /by 2025-02-10"));
        assertInstanceOf(AddCommand.class, Parser.parseCommand("event Conference /from 2025-02-10 /to 2025-02-12"));
        assertInstanceOf(FindCommand.class, Parser.parseCommand("find 2025-02-10"));
    }

    @Test
    void parseCommand_invalidNumberFormat_throwsDonkException() {
        assertThrows(DonkException.class, () -> Parser.parseCommand("mark abc"));
        assertThrows(DonkException.class, () -> Parser.parseCommand("unmark xyz"));
        assertThrows(DonkException.class, () -> Parser.parseCommand("delete one"));
    }

    @Test
    void parseCommand_invalidTaskFormat_throwsDonkException() {
        assertThrows(DonkException.class, () -> Parser.parseCommand("todo"));
        assertThrows(DonkException.class, () -> Parser.parseCommand("deadline Submit report"));
        assertThrows(DonkException.class, () -> Parser.parseCommand("event Conference /from 2025-02-10"));
    }

    @Test
    void parseCommand_unknownCommand_returnsInvalidCommand() throws DonkException {
        assertTrue(Parser.parseCommand("randomCommand") instanceof InvalidCommand);
        assertTrue(Parser.parseCommand("unknown 123") instanceof InvalidCommand);
    }

    @Test
    void parseTask_validTasks_returnCorrectTask() throws DonkException {
        Task todo = Parser.parseTask("T | 1 | Read book");
        assertTrue(todo instanceof ToDo);
        assertEquals("Read book", todo.getName());
        assertTrue(todo.getStatus());

        Task deadline = Parser.parseTask("D | 0 | Submit report | 2025-02-10");
        assertTrue(deadline instanceof Deadline);
        assertEquals("Submit report", deadline.getName());
        assertFalse(deadline.getStatus());

        Task event = Parser.parseTask("E | 1 | Conference | 2025-02-10 | 2025-02-12");
        assertTrue(event instanceof Event);
        assertEquals("Conference", event.getName());
        assertTrue(event.getStatus());
    }

    @Test
    void parseTask_invalidFormat_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> Parser.parseTask("X | 0 | Invalid task"));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Parser.parseTask("D | 1 | Incomplete Deadline"));
    }
}
