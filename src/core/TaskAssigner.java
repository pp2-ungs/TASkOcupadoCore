package core;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import obs.Observer;
import discovery.Discoverer;

// Asigna tareas a miembros, y sabe qué tareas se asignaron a cuáles miembros.
public class TaskAssigner implements obs.Observable {
	
	private Set<Observer> observers;
	private Map<Task, Set<Member>> assignedTasks;

	// TODO
	public TaskAssigner() {
		try {
			Set<Object> objects = Discoverer.discover("./", obs.Observer.class);
			
			observers = objects.stream()
			        .filter(obj -> obj instanceof obs.Observer)
			        .map(obj -> (obs.Observer) obj)
			        .collect(Collectors.toSet());
				
			assignedTasks = new HashMap<>();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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

    	// FIXME: como tenemos Observer y no notificator, no tenemos .notify
    	observers.forEach(observer -> observer.update(notification));
    }

}
