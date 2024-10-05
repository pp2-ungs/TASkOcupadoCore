package core;

import java.util.HashMap;
import java.util.Set;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

// Responsabilidad: asignar las tareas.
public class TaskAssigner implements Observable {

    private Map<Task, Set<Member>> taskAssignment;
    private Set<Observer> observers;

    public TaskAssigner(Set<Observer> observers) {
        this.observers = observers;
    }

    public void assignTask(Task task, Member member) {
        // TODO: checkeos y excepciones
        taskAssignment.get(task).add(member);
        
        var timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm'hs'"));
        //var notification = "(" + timestamp + ")  Task: [" + task.getDescription() + "]  →  Member: [" + member.getName() + "]\n";

        //\begin{FIXME}
        var msg = new HashMap<String, String>();
        msg.put("Email", "ebertz.xime@gmail.com");
        msg.put("Task", task.getDescription());
        msg.put("Name", member.getName());
        msg.put("Time", timestamp);
        //\end{FIXME}

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
