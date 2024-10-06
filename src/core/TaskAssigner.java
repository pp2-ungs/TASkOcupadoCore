package core;

import observer.Observer;
import observer.Observable;
import java.util.HashMap;
import java.util.Set;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Map;

// Responsabilidad: asignar las tareas.
public class TaskAssigner implements Observable {

    private Map<Task, Set<Member>> assignedTasks;
    private Set<Observer> observers;

    public TaskAssigner(Set<Observer> observers) {
        this.assignedTasks = new HashMap<>();
        this.observers = observers;
    }

    public void assignTask(Task task, Member member) {
        if (assignedTasks.get(task) == null) {
            assignedTasks.put(task, new HashSet<>());
        }
        assignedTasks.get(task).add(member);
        
        var timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm'hs'"));
        //var time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm'hs'"));
        //var notification = "(" + timestamp + ")  Task: [" + task.getDescription() + "]  →  Member: [" + member.getName() + "]\n";

        //\begin{FIXME}
        var msg = new HashMap<String, String>();
        msg.put("Task", task.getDescription());
        
        // \begin{ESTO NO VA}
        msg.put("Email", "PUT YOUR EMAIL HERE TO TEST");
        msg.put("TelegramId", "PUT YOUR TELEGRAM UID HERE TO TEST");
        // \end{ESTO NO VA}
        
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

	// Para la UI, quizás no está muy bien.
    public Set<Observer> getNotificationMethods() {
        return observers;
    }

}
