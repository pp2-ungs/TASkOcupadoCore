package core;

import java.util.HashSet;
import java.util.Set;

public class CalendarLoader implements DataLoader {

    @Override
    public Set<Member> loadMembers() {
        return new HashSet<>();
    }

    @Override
    public Set<Task> loadTasks() {
        return new HashSet<>();
    }

}
