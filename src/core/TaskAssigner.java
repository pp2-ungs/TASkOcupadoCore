package core;

import annotation.Notificator;
import observer.Observer;
import observer.Observable;
import java.util.HashMap;
import java.util.Set;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Map;

public class TaskAssigner implements Observable {

    private Map<Task, Set<Member>> assignedTasks;
    private Map<Observer, Boolean> activeObservers;

    public TaskAssigner(Set<Observer> observers) {
        assignedTasks = new HashMap<>();
        activeObservers = new HashMap<>();
        observers.forEach(obs -> activeObservers.put(obs, false));
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
        for (Observer observer : activeObservers.keySet()) {
            boolean isActive = activeObservers.get(observer);
            if (observer.getClass().isAnnotationPresent(Notificator.class)) {
                notificators.add(observer);
            }
        }

        //Set<Observer> notificators = (Set<Observer>) observersActive.keySet().stream().filter(obs -> obs.getClass().isAnnotationPresent(Notificator.class));
        System.out.println("Notifiers: " + notificators);
        return notificators;
    }

    public void activateObserver(Observer observer) {
        activeObservers.replace(observer, true);
    }

    public void deactivateObserver(Observer observer) {
        activeObservers.replace(observer, false);
    }

    @Override
    public void addObserver(Observer observer) {
        activeObservers.put(observer, true);
        System.out.println("Observer agregado: " + observer.getClass().getSimpleName());
    }

    @Override
    public void removeObserver(Observer observer) {
        activeObservers.remove(observer);
    }

    @Override
    public void notifyObservers(Object event) {
        for (Observer obs : activeObservers.keySet()) {
            boolean isActive = activeObservers.get(obs);
            if (isActive) try {
                obs.update(event);
            } catch (Exception ex) {
                System.err.println("?notification not delivered to " + obs.getClass().getSimpleName());
            }
        }
    }
}
