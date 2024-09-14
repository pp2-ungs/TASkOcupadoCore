package core;

import java.util.Set;

public class TASkOcupado {
	
	private TaskAssigner taskAssigner;
	private Set<Task> tasks;
	
	public TASkOcupado() {
		taskAssigner = new TaskAssigner(obtainMembersFromCalendar(null));
		tasks = obtainTasksFromCalendar(null);
	}
	
	private Set<Member> obtainMembersFromCalendar(Calendar c) {
		return null;
	}
	
	private Set<Task> obtainTasksFromCalendar(Calendar c) {
		return null;
	}

	public void assignTaskToMember(Task t, Member m) {
		this.taskAssigner.assignTaskToMember(t, m);
	}
}
