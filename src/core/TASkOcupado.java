package core;

import java.util.HashSet;
import java.util.Set;

public class TASkOcupado {
	
	private TaskAssigner taskAssigner;
	private Set<Member> group;
	private Set<Task> tasks;
	
	public TASkOcupado() {
		taskAssigner = new TaskAssigner();
		
		group = new HashSet<Member>();
		tasks = new HashSet<Task>();
	}

	public void addMember(String member) {
		// TODO: call to an external class?
		getTasksFromCalendars(member);	// we're doing two things
	}
	
	private void getTasksFromCalendars(String member) {
		// TODO: 1. this is not void, 2. think about this
	}
	
	public void assignTask(String task, String member) {
		// TODO: this is really a search from the two sets
		Task t = new Task(task);
		Member m = new Member(member);
		this.taskAssigner.assignTask(t, m);
	}
}
