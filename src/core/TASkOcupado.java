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

    public TASkOcupado(String propertiesPath) {
        CoreFactory helper = new CoreFactory(propertiesPath);

        tasks = helper.getTasks();
        members = helper.getMembers();

        observers = new HashSet<>();

        Set<Observer> taskAssignerObservers = helper.getObservers();
        taskAssigner = new TaskAssigner(taskAssignerObservers);

        taskAssigner.addObserver(this);

        // TODO: init, ver que onda
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

    public Set<Observer> getNotificators() {
        return taskAssigner.getNotificators();
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
