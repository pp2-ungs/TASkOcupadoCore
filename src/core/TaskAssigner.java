package core;

import annotation.Notificator;
import java.rmi.RemoteException;
import observer.Observer;
import observer.Observable;
import java.util.HashMap;
import java.util.Set;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import remote.RemoteObservable;
import remote.RemoteObserver;

public class TaskAssigner implements Observable {

    private Map<Task, Set<Member>> assignedTasks;
    private Map<Observer, Boolean> observersActive;

    public TaskAssigner(Set<Observer> observers) {
        assignedTasks = new HashMap<>();
        observersActive = new HashMap<>();
        observers.forEach(obs -> observersActive.put(obs, true));
    }

    public void assignTask(Task task, Member member) {
        assign(task, member);
        notifyAssignment(task, member);
    }

    private void assign(Task task, Member member) {
        if (assignedTasks.get(task) == null) {
            assignedTasks.put(task, new HashSet<>());
        }
        assignedTasks.get(task).add(member);
    }

    private void notifyAssignment(Task task, Member member) {
        var timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm'hs'"));

        var msg = new HashMap<String, String>();
        msg.put("Task", task.getDescription());
        msg.put("Name", member.getName());
        msg.put("Time", timestamp);

        notifyObservers(msg);
    }

    public Set<Observer> getNotificators() {
        Set<Observer> notificators = new HashSet<>();
        for (Observer observer : observersActive.keySet()) {
            boolean isActive = observersActive.get(observer);
            if (observer.getClass().isAnnotationPresent(Notificator.class) && isActive) {
                notificators.add(observer);
            }
        }
        
        //Set<Observer> notificators = (Set<Observer>) observersActive.keySet().stream().filter(obs -> obs.getClass().isAnnotationPresent(Notificator.class));
        System.out.println("Notificators: " + notificators);
        return notificators;
    }
    
    public void activateObserver(Observer observer) {
        observersActive.replace(observer, true);
    }
    
    public void deactivateObserver(Observer observer) {
        observersActive.replace(observer, false);
    }

    @Override
    public void addObserver(Observer observer) {
        observersActive.put(observer, true);
        System.out.println("Observer agregado: " + observer.getClass().getSimpleName());
    }

    @Override
    public void removeObserver(Observer observer) {
        observersActive.remove(observer);
    }

    @Override
    public void notifyObservers(Object event) {
        for (Observer obs: observersActive.keySet()) {
            boolean isActive = observersActive.get(obs);
            if (isActive) try {
                obs.update(event);
            } catch (RemoteException ex) {
                Logger.getLogger(TaskAssigner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
