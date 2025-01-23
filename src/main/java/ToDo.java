public class ToDo extends Task{

    public ToDo(String name) throws DonkException {
        super(name, false);
        if (name == null || name.trim().isEmpty()) {
            throw new DonkException("Oops!!! You must type in the description of the ToDo task.");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
