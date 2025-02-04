public class Deadline extends Task{

    protected String by;

    public Deadline(String name, String by, boolean status) throws DonkException {
        super(name, status);
        this.by = by;
        if (name == null || name.trim().isEmpty())
            throw new DonkException("Oops!!! You must type in the description of the Deadline task.");
        if (by == null || by.trim().isEmpty())
            throw new DonkException("Oops!!! Your Deadline task must have a due time.");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (getStatus() ? "1" : "0") + " | " + getName() + " | " + by;
    }

    public String getBy() {
        return this.by;
    }


}
