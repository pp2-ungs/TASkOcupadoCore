package core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// FIXME
// Ya no conoce al Discoverer
//
// ???
// Dijo Javier que esto no debería ser observable? no se oye bien en el audio.
public class TaskAssigner implements Observable {

    private Map<Task, Set<Member>> assignedTasks;
    private Set<Observer> observers;

    public TaskAssigner(Set<Observer> observers) {
        this.assignedTasks = new HashMap<>();
        this.observers = observers;
    }

    public void assignTask(Task task, Member member) {
        if (!assignedTasks.containsKey(task)) {
            assignedTasks.put(task, new HashSet<>());
        }
        assignedTasks.get(task).add(member);
        notifyObservers(task, member);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers(Task task, Member member) {
        NotificationDTO notificationDTO = NotificationBuilder.createNotificationDTO(task, member);
        
        // FIXME: como tenemos Observer y no notificator, no tenemos .notify
        observers.forEach(observer -> observer.update(notificationDTO));
    }

}
