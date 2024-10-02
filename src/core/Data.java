package core;

import java.util.Set;

// FIXME
public class Data {

    private Set<Member> members;
    private Set<Task> tasks;

    public Data() {
        DataSetLoader loader = (DataSetLoader) PluginFactory.loadInstance(DataSetLoader.class);
        
        members = loader.loadSet(Member.class);
        tasks = loader.loadSet(Task.class);
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
