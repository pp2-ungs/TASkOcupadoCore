package core;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class TaskAssigner implements Observable {

    private Map<Task, Set<Member>> assignedTasks;
    private Set<Observer> observers;

    public TaskAssigner() {
        assignedTasks = new HashMap<>();
        try {
            Set<Object> objects = Discoverer.discover(AppSettings.TASKOCUPADO_EXT_DIR, Observer.class);

            observers = objects.stream()
                    .map(obj -> (Observer) obj)
                    .collect(Collectors.toSet());
        } catch (FileNotFoundException e) {
            observers = new HashSet<>();
            e.printStackTrace();
        }
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
        String timeStampOfNotification = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm'hs'"));
        String notification = timeStampOfNotification + " Task: [" + task.getDescription() + "] → Member: [" + member.getName() + "]\n";

        // FIXME: como tenemos Observer y no notificator, no tenemos .notify
        observers.forEach(observer -> observer.update(notification));
    }

}
