package core;

import java.util.Set;

public interface DataLoader {
    
    public Set<Member> loadMembers();

    public Set<Task> loadTasks();

}
