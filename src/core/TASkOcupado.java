package core;

import observer.*;
import java.util.HashSet;
import java.util.Set;
import remote.RemoteServer;

public class TASkOcupado implements Observable, Observer {

    private TaskAssigner taskAssigner;
    private Set<Task> tasks;
    private Set<Member> members;
    private Set<Observer> observers;
    
    public TASkOcupado(Set<Task> tasks, Set<Member> members, TaskAssigner taskAssigner) {
        this.tasks = tasks;
        this.members = members;
        this.taskAssigner = taskAssigner;
        
        observers = new HashSet<>();
        taskAssigner.addObserver(this);
        
        new RemoteServer().startServer(taskAssigner);
    }

    public void assignTask(Task task, Member member) {
        taskAssigner.assignTask(task, member);
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public Set<Observer> getNotifiers() {
        return taskAssigner.getNotifiers();
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
        observers.forEach(observer -> {
            try {
                observer.update(event);
            } catch (Exception ex) {
                // esto para todo lo demás, añadido de miembros, de tareas, bla
                System.err.println("?notification not delivered to " + observer.getClass().getSimpleName());
            }
        });
    }

    @Override
    public void update(Object event) {
        notifyObservers(event);
    }

    // TODO: fixname, abstraction leak
    public void activeTaskAssignerObserver(Observer observer) {
        taskAssigner.activateObserver(observer);
    }

    public void deactiveObserverToTaskAssigner(Observer observer) {
        taskAssigner.deactivateObserver(observer);
    }
}
