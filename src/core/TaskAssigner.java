package core;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

// Asigna tareas a miembros, y sabe qué tareas se asignaron a cuáles miembros.
public class TaskAssigner implements Observable {

    private Map<Task, Set<Member>> assignedTasks;
    private Set<Observer> observers;

    // TODO
    public TaskAssigner() {
        assignedTasks = new HashMap<>();
        try {
            Set<Object> objects = Discoverer.discover("../TASkOcupadoExt/src/ext/", Observer.class);

            observers = objects.stream()
                    .filter(obj -> obj instanceof Observer)
                    .map(obj -> (Observer) obj)
                    .collect(Collectors.toSet());
            System.out.println("Observers encontrados:" + observers);

        } catch (FileNotFoundException e) {
            observers = new HashSet<>();
            e.printStackTrace();
        }
    }

    // TODO
    public void assignTask(Task t, Member m) {
        if (!assignedTasks.containsKey(t)) {
            assignedTasks.put(t, new HashSet<>());
        }
        assignedTasks.get(t).add(m);

        notifyObservers(t, m);
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
        String timeStampOfNotification = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm'hs'"));
        String notification = timeStampOfNotification + " Task: [" + task.getDescription() + "] → Member: [" + member.getName() + "]\n";

        // FIXME: como tenemos Observer y no notificator, no tenemos .notify
        observers.forEach(observer -> observer.update(notification));
    }

}
