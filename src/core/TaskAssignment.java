package core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Responsabilidad: asignar las tareas.
public class TaskAssignment {
    
    private Map<Task, Set<Member>> assignedTasks;

    public TaskAssignment() {
        this.assignedTasks = new HashMap<>();
    }

    public void assignTask(Task task, Member member) {
        if (!assignedTasks.containsKey(task)) {
            assignedTasks.put(task, new HashSet<>());
        }
        assignedTasks.get(task).add(member);
    }

}
