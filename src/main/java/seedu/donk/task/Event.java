package seedu.donk.task;

import seedu.donk.DonkException;
import seedu.donk.parseDate;

import java.time.LocalDate;

public class Event extends Task {
    private String start;
    private String end;

    private String processedStart;
    private String processedEnd;

    public Event(String name, String start, String end, boolean status) throws DonkException {
        super(name, status);
        this.start = start;
        this.end = end;

        if (name == null || name.trim().isEmpty())
            throw new DonkException("Oops!!! You must type in the description of the Event task.");
        if (start == null || start.trim().isEmpty())
            throw new DonkException("Oops!!! Your Event task must have a start time.");
        if (end == null || end.trim().isEmpty())
            throw new DonkException("Oops!!! Your Event task must have a end time.");

        if (!parseDate.judgeStartAndEnd(start, end)) {
            throw new DonkException("Oops!!! Your event end time is earlier than its start time.");
        }

        processedStart = parseDate.parseDateOrReturnOriginal(start);
        processedEnd = parseDate.parseDateOrReturnOriginal(end);
    }

    @Override
    public String toString() {
        LocalDate d1 = LocalDate.parse(start);
        LocalDate d2 = LocalDate.parse(end);

        return "[E]" + super.toString() + " (from: " + processedStart + " to: " + processedEnd + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (getStatus() ? "1" : "0") + " | " + getName() + " | " + start + " | " + end;
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

}
