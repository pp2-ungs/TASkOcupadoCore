package core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observer;
import java.util.Set;

// Asigna tareas a miembros, y sabe qué tareas se asignaron a cuáles miembros.
public class TaskAssigner {
	
	@SuppressWarnings("deprecation")
	private Set<Observer> observers;
	private Map<Task, Set<Member>> assignedTasks;	// not taskAssigned jsajaja

	// TODO
	public TaskAssigner() {
		observers = new HashSet<>();
		assignedTasks = new HashMap<>();
	}

	// TODO
	public void assignTask(Task t, Member m) {
		assignedTasks.putIfAbsent(t, new HashSet<>());
		assignedTasks.get(t).add(m);
		System.out.println("Viva Perón!");
	}
	
	// TODO: think about if we really need this
	public boolean hasChanged() {
		return false;
	}

}
