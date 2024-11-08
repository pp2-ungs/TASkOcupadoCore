package core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import observer.Observer;
import java.util.Set;
import notifier.Notifier;
import notifier.NotifiersActivator;
import observer.Observable;

public class TASkOcupado implements Observable {

    private Set<Task> tasks;
    private Set<Person> people;
    private TaskAssigner taskAssigner;
    private Set<Observer> observers;
    private NotifiersActivator notifiersActivator;

    public TASkOcupado(Set<Task> tasks, Set<Person> people) {
        this.tasks = tasks;
        this.people = people;
        this.taskAssigner = new TaskAssigner();
        this.observers = new HashSet<Observer>();
        this.notifiersActivator = new NotifiersActivator();
    }

    public void assignTask(Task task, Person member) {
        taskAssigner.assignTask(task, member);
        notifyAssignment(task, member);
    }

    private void notifyAssignment(Task task, Person people) {
        var timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm'hs'"));

        var msg = new HashMap<String, String>();
        msg.put("Task", task.getDescription());
        msg.put("Name", people.getName());
        msg.put("Time", timestamp);

        notifyObservers(msg);
    }

    @Override
    public void addObserver(Observer observer) {
        if (observer instanceof Notifier) {
            notifiersActivator.addNotifier((Notifier) observer);
        } else {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Object event) {
        observers.forEach(observer -> observer.update(event));
        notifiersActivator.getActiveNotifiers().forEach(notifier -> notifier.update(event));
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public Set<Person> getPeople() {
        return people;
    }
    
    public Set<String> getNotifiers() {
        return notifiersActivator.getNotifiers();
    }

    public void activateNotifier(String notifier) {
        notifiersActivator.activateNotifier(notifier);
    }

    public void deactivateNotifier(String notifier) {
        notifiersActivator.deactivateNotifier(notifier);
    }

}
