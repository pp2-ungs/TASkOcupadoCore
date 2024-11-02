package core;

import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;

public class TaskAssigner {

    private Map<Task, Set<Person>> assignedTasks;

    public TaskAssigner() {
        this.assignedTasks = new HashMap<>();
    }

    public void assignTask(Task task, Person person) {
        assign(task, person);
    }

    private void assign(Task task, Person person) {
        if (assignedTasks.get(task) == null) {
            assignedTasks.put(task, new HashSet<>());
        }
        assignedTasks.get(task).add(person);
    }

}
