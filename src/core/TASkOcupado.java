package core;

import java.util.Set;
import java.util.stream.Collectors;

// Responsabilidad: notificar cambios en asignaciones, miembros y tareas
// (pensar si se puede escribir mejor) (este es el modelo)

public class TASkOcupado implements core.Observable {
    private Set<Observer> observers;
    private Set<Task> tasks;
    private Set<Member> members;
    private TaskAssigner taskAssigner;
    
    public TASkOcupado(String propertiesPath) {
        
    }
    
    public boolean assignTask(Task task, Member member) {
        return true;
    }
    
    public Set<Task> getTasks() {
        return tasks;
    }
    
    public Set<Member> getMembers() {
        return members;
    }
    
    @Override
    public void addObserver(Observer observer) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeObserver(Observer observer) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void notifyObservers(Object event) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
}
