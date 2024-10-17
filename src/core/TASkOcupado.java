package core;

import annotation.Notificator;
import java.rmi.RemoteException;
import observer.*;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import remote.RemoteServer;

public class TASkOcupado implements Observable, Observer {
    
    private Set<Observer> observers;
    private Set<Task> tasks;
    private Set<Member> members;
    private TaskAssigner taskAssigner;
    
    public TASkOcupado(String propertiesPath) {
        TASkOcupadoHelper helper = new TASkOcupadoHelper(propertiesPath);
             
        tasks = helper.getTasks();
        members = helper.getMembers();
        
        observers = new HashSet<>();
        
        Set<Observer> taskAssignerObservers = helper.getObservers();
        taskAssigner = new TaskAssigner(taskAssignerObservers);
        
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
                Logger.getLogger(TASkOcupado.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void update(Object event) {
        notifyObservers(event);
    }
    
    public void activeTaskAssignerObserver(Observer observer) {
        taskAssigner.activateObserver(observer);
    }

    public void deactiveObserverToTaskAssigner(Observer observer) {
        taskAssigner.deactivateObserver(observer);
    }
    
}
