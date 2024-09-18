package plugin;

import java.util.Set;

import core.Member;
import core.Task;

public interface DataLoader {
	
	public Set<Member> loadMembers();
	public Set<Task> loadTasks();

}
