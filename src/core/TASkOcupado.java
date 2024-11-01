package core;

import observer.Observer;
import java.util.HashSet;
import java.util.Set;
import observer.Observable;

public class TASkOcupado implements Observable, Observer {
    private Set<Observer> observers;
    private Set<Task> tasks;
    private Set<Person> members;
    private TaskAssigner taskAssigner;
    
    public TASkOcupado(Set<Task> tasks, Set<Person> members, TaskAssigner taskAssigner) {
        this.tasks = tasks;
        this.members = members;
        this.taskAssigner = taskAssigner;
        
        observers = new HashSet<>();
        taskAssigner.addObserver(this);
    }
    
    public void assignTask(Task task, Person member) {
        taskAssigner.assignTask(task, member);
    }
    
    public Set<Task> getTasks() {
        return tasks;
    }
    
    public Set<Person> getMembers() {
        return members;
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

    @Override
    public void update(Object event) {
        notifyObservers(event);
    }
}
