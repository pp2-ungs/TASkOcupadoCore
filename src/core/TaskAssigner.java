package core;

import java.util.Map;
import java.util.Set;

public class TaskAssigner {
	
	private Map<Task, Set<Member>> tasksAssigned;
	
	public TaskAssigner() {
		this.tasksAssigned = null;
	}

	public void assignTask(Task t, Member m) {
		// tasksAssigned ← task, member
		System.out.println("Viva Perón!");
	}

}
