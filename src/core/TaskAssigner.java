package core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

// Asigna tareas a miembros, y sabe qué tareas se asignaron a cuáles miembros.
@SuppressWarnings("deprecation")
public class TaskAssigner extends Observable {
	
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
		
		notifyObservers(t, m);
		//System.out.println("Viva Perón!");
	}
	
	// TODO: think about if we really need this
	public boolean hasChanged() {
		return false;
	}
	
	public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    
    private void notifyObservers(Task t, Member m) {
    	String notification = "Se le asignó la tarea " + t.getDescription() + " al miembro " + m.getName();
        observers.forEach(observer -> observer.update(this, notification));
    }

}
