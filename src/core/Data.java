package core;

import java.util.Set;

public class Data {

    private Set<Member> members;
    private Set<Task> tasks;

    public Data() {
        DataLoader dataLoader = (DataLoader) PluginFactory.loadInstance(DataLoader.class);
        
        members = dataLoader.loadMembers();
        tasks = dataLoader.loadTasks();
    }

    public Member obtainMember(String name) {
        return members.stream()
                .filter(m -> m.getName().equals(name))
                .findFirst()
                .orElse(new NullMember());
    }

    public Task obtainTask(String taskDescription) {
        return tasks.stream()
                .filter(t -> t.getDescription().equals(taskDescription))
                .findFirst()
                .orElse(new NullTask());
    }

    public String[] getMembers() {
        return members.stream()
                .map(Member::toString)
                .toArray(String[]::new);
    }

    public String[] getTasks() {
        return tasks.stream()
                .map(Task::toString)
                .toArray(String[]::new);
    }

}
