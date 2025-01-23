public class ToDo extends Task{

    public ToDo(String name) {
        super(name, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
