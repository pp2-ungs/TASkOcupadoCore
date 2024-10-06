package core;

import java.util.HashSet;
import java.util.Set;

// Responsabilidad: notificar cambios en asignaciones, miembros y tareas
// (pensar si se puede escribir mejor) (este es el modelo)

public class TASkOcupado implements core.Observable, core.Observer {
    private Set<Observer> observers;
    private Set<Task> tasks;
    private Set<Member> members;
    private TaskAssigner taskAssigner;
    
    public TASkOcupado(String propertiesPath) {
        TASkOcupadoFactory factory = new TASkOcupadoFactory(propertiesPath);
             
        tasks = factory.getTasks();
        members = factory.getMembers();
        
        observers = new HashSet<>();
        
        Set<Observer> taskAssignerObservers = factory.getObservers();
        taskAssigner = new TaskAssigner(taskAssignerObservers);
        
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
    
    // FIXME: codigo repetido...
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
