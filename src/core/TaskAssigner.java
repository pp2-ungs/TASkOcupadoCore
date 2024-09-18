package core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observer;
import java.util.Set;

// Asigna tareas a miembros, y sabe qué tareas se asignaron a cuáles miembros.
@SuppressWarnings("deprecation")
public class TaskAssigner implements obs.Observable {
	
	private Set<Observer> observers;
	private Map<Task, Set<Member>> assignedTasks;

	// TODO
	public TaskAssigner() {
		// TODO: buscar los observers en con Discovery
		observers = new HashSet<>();
		assignedTasks = new HashMap<>();
	}

	// TODO
	public void assignTask(Task t, Member m) {
		if (!assignedTasks.containsKey(t)) {
			assignedTasks.put(t, new HashSet<>());
		}
		assignedTasks.get(t).add(m);
		
		notifyObservers(t, m);
	}
	
	public void debug() {
		System.out.println("StartDebugging");
		System.out.println(assignedTasks.get(new Task("Ir a entrenar")));
		for (Task t : assignedTasks.keySet()) {
			System.out.print(t + ": " + t.getDescription() + " → Members: ");
			for (Member m : assignedTasks.get(t)) {
				System.out.print(m.getName() + ", ");
			}
			System.out.println();
        }
		System.out.println("StopDebugging");
	}
	
	@Override
	public void addObserver(Observer observer) {
        observers.add(observer);
    }

	@Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    
    private void notifyObservers(Task t, Member m) {
    	String notification = "Se le asignó la tarea '" + t.getDescription() + "' al miembro " + m.getName();
    	
    	// FIXME: this is not ok! it's just because we don't extend Observable
    	// esto a mano
    	observers.forEach(observer -> observer.update(null, notification));
    }

}
