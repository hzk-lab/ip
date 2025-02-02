public class Event extends Task{

    protected String start;
    protected String end;

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

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (getStatus() ? "1" : "0") + " | " + getName();
    }
}
