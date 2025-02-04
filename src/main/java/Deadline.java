import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    protected String by;
    private String processedBy;

    public Deadline(String name, String by, boolean status) throws DonkException {
        super(name, status);
        this.by = by;
        if (name == null || name.trim().isEmpty())
            throw new DonkException("Oops!!! You must type in the description of the Deadline task.");
        if (by == null || by.trim().isEmpty())
            throw new DonkException("Oops!!! Your Deadline task must have a due time.");

        processedBy = parseDate.parseDateOrReturnOriginal(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + processedBy + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (getStatus() ? "1" : "0") + " | " + getName() + " | " + this.processedBy;
    }
}
