import java.util.ArrayList;
import java.util.List;
public class TaskList {

    private static final int MAX_TASKS = 100;
    private List<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        if (tasks.size() < MAX_TASKS) {
            tasks.add(task);
            System.out.println("Got it!");
            System.out.println("added task: " + task);
            System.out.println("Now Barbie has " + tasks.size() + " tasks in list\n");

        } else {
            System.out.println("Way too many tasks for today Barbie!");
            System.out.println("Slow the Slayy\n");

        }
    }
    public void addTodoTask(String description) throws KenException {
        if (description.isEmpty()) {
            throw new KenException("do what?");
        }
        Todo todo = new Todo(description);
        addTask(todo);
    }

    public void addDeadlineTask(String description) throws KenException {
        try {
            int indexOfBy = description.indexOf("/by");
            if (indexOfBy != -1) {
                String deadlineDescription = description.substring(0, indexOfBy).trim();
                String by = description.substring(indexOfBy + 3).trim();
                Deadline deadline = new Deadline(deadlineDescription, by);
                addTask(deadline);
            } else {
                System.out.println("That's not how you declare a deadline. p.s. use /by.");
                throw new KenException("Invalid deadline command. By when?.");
            }
        } catch (Exception e) {
            throw new KenException("Invalid deadline command. By when?.");
        }
    }

    public void addEventTask(String description) throws KenException {
        try {
            int indexOfFrom = description.indexOf("/from");
            int indexOfTo = description.indexOf("/to");

            if (indexOfFrom != -1 && indexOfTo != -1) {
                String eventDescription = description.substring(0, indexOfFrom).trim();
                String from = description.substring(indexOfFrom + 5, indexOfTo).trim();
                String to = description.substring(indexOfTo + 3).trim();
                Event event = new Event(eventDescription, from, to);
                addTask(event);
            } else {
                System.out.println("That's not how you declare an event. p.s. use /from, and /to.");
                throw new KenException("Invalid event command. From when to when?");
            }
        } catch (Exception e) {
            throw new KenException("Invalid event command. From when to when?");
        }
    }

    public void deleteTask(int index) {
        if (index >= 1 && index <= tasks.size()) {
            Task removedTask = tasks.remove(index - 1);
            System.out.println("Ohh okayy...");
            System.out.println("deleted task: " + removedTask);
            System.out.println("Now Barbie has " + tasks.size() + " tasks in list.\n");
        } else {
            System.out.println("Barbie has no task " + index);
        }
    }
    public void markTask(int index) {
        if (index >= 1 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            task.markAsDone();
            System.out.println("SUBLIME! Task " + index + " completed!\n " + task.toString());
        } else {
            System.out.println("Barbie has no task " + index);
        }
    }

    public void unmarkTask(int index) {
        if (index >= 1 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            task.unmarkAsDone();
            System.out.println("ookayy, so task " + index + " is not actually done\n " + task.toString());
            System.out.println("You are not doing task very well :(");

        } else {
            System.out.println("Barbie has no task " + index);
        }
    }

    public void listTasks() {

        System.out.println("Hold my ice cream,");

        if (tasks.isEmpty()) {
            System.out.println("actually, wait, i'm taking my ice cream back");
            System.out.println("no tasks yet");
        } else {
            System.out.println("Your tasks for today: \n");

            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

}
