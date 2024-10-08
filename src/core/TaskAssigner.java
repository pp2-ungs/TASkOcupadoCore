package core;

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
    private Set<Observer> observers;

    public TaskAssigner(Set<Observer> observers) {
        this.assignedTasks = new HashMap<>();
        this.observers = observers;
    }

    public void assignTask(Task task, Member member) {
        if (assignedTasks.get(task) == null) {
            assignedTasks.put(task, new HashSet<>());
        }
        assignedTasks.get(task).add(member);
        
        var timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm'hs'"));

        var msg = new HashMap<String, String>();
        msg.put("Task", task.getDescription());
        msg.put("Name", member.getName());
        msg.put("Time", timestamp);

        notifyObservers(msg);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Object event) {
        observers.forEach(observer -> observer.update(event));
    }
}
