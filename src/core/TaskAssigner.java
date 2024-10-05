package core;

import java.util.HashMap;
import java.util.Set;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Responsabilidad: asignar las tareas a notificar.
public class TaskAssigner implements Observable {

    private TaskAssignment taskAssignment;
    private Set<Observer> observers;

    public TaskAssigner(TaskAssignment taskAssignment, Set<Observer> observers) {
        this.taskAssignment = taskAssignment;
        this.observers = observers;
    }

    public void assignTask(Task task, Member member) {
        taskAssignment.assignTask(task, member);
        var timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm'hs'"));
        //var notification = "(" + timestamp + ")  Task: [" + task.getDescription() + "]  →  Member: [" + member.getName() + "]\n";

        //\begin{FIXME}
        var msg = new HashMap<String, String>();
        msg.put("Task", task.getDescription());
        msg.put("Name", member.getName());
        msg.put("Time", timestamp);
        msg.put("Email", member.getEmail());
        msg.put("TelegramId", Integer.toString(member.getTelegramId()));
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
