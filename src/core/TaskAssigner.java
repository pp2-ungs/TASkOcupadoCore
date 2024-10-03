package core;

import java.util.Set;

// Responsabilidad: notificar las asignaciones a los observers.
public class TaskAssigner implements Observable {

    private TaskAssignment taskAssignment;
    private Set<Observer> observers;

    public TaskAssigner(TaskAssignment taskAssignment, Set<Observer> observers) {
        this.taskAssignment = taskAssignment;
        this.observers = observers;
    }

    public void assignTask(Task task, Member member) {
        taskAssignment.assignTask(task, member);
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

    // FIXME
    private void notifyObservers(Task task, Member member) {
        NotificationDTO notificationDTO = NotificationBuilder.createNotificationDTO(task, member);
        
        // FIXME: como tenemos Observer y no notificator, no tenemos .notify
        observers.forEach(observer -> observer.update(notificationDTO));
    }

}
