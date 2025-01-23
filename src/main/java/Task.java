public class Task {
    private String name;
    private boolean status;

    public Task(String name, boolean status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return this.name;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void markAsDone() {
        this.status = true;
    }

    public void markAsUndone() {
        this.status = false;
    }

    @Override
    public String toString() {
        if (this.status) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}
