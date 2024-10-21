package core;

import observer.Observer;
import java.util.HashSet;
import java.util.Set;

public class TASkOcupado implements observer.Observable, observer.Observer {
    private Set<Observer> observers;
    private Set<Task> tasks;
    private Set<Member> members;
    private TaskAssigner taskAssigner;
    
    public TASkOcupado(String propertiesPath) {
        CoreFactory helper = new CoreFactory(propertiesPath);
             
        tasks = helper.getTasks();
        members = helper.getMembers();
        
        observers = new HashSet<>();
        
        Set<Observer> taskAssignerObservers = helper.getObservers();
        taskAssigner = new TaskAssigner(taskAssignerObservers);
        
        taskAssigner.addObserver(this);
    }
    
    public TASkOcupado(Set<Task> tasks, Set<Member> members, TaskAssigner taskAssigner) {
        this.tasks = tasks;
        this.members = members;
        this.taskAssigner = taskAssigner;
        
        observers = new HashSet<>();
        taskAssigner.addObserver(this);
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
