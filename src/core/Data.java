package core;

import java.util.Set;

public class Data {

    private Set<Member> members;
    private Set<Task> tasks;

    public Data() {
        DataLoader dataLoader = PluginFactory.loadInstances();
        
        members = dataLoader.loadMembers();
        tasks = dataLoader.loadTasks();
    }

    // TODO: hacer bien
    // no son getters
    public Member obtainMember(String name) {
        return members.stream()
                .filter(m -> m.getName().equals(name))
                .findFirst()
                .orElse(new Member("<Unknown member>")); // FIXME: patrón Special Case / Null Object
    }

    public Task obtainTask(String taskDescription) {
        return tasks.stream()
                .filter(t -> t.getDescription().equals(taskDescription))
                .findFirst()
                .orElse(new Task("<Unknown task>")); // FIXME
    }

    public Object[] getMembers() {
        return members.stream()
                .map(Member::toString)
                .toArray(String[]::new);
    }

    public Object[] getTasks() {
        return tasks.stream()
                .map(Task::toString)
                .toArray(String[]::new);
    }

}