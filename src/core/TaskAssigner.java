package core;

import java.util.Set;

// FIXME

// Textual del documento:
//
// "US1: Notificación de asignación de tareas
// Quiero notificar a un miembro  cuando se le asigne una tarea."
//
// Teniendo la US1 en mente, y lo que puse de responsabilidad de esta clase, es
// claro que esta clase es el core de la funcionalidad.
// 
// Responsabilidad: asignar las tareas a notificar.
public class TaskAssigner implements Observable {

    private TaskAssignment taskAssignment;
    private Set<Observer> observers;

    public TaskAssigner(TaskAssignment taskAssignment, Set<Observer> observers) {
        this.taskAssignment = taskAssignment;
        this.observers = observers;
    }

    // Hace dos cosas.
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
