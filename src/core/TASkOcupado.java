package core;

import java.util.HashSet;
import java.util.Set;

public class TASkOcupado {
	
	private TaskAssigner taskAssigner;
	private Set<Member> group;
	private Set<Task> tasks;
	
	public TASkOcupado(TaskAssigner t) {
		taskAssigner = new TaskAssigner();
		group = new HashSet<>();
		tasks = new HashSet<>();
	}

	public void addMember(String member) {
		// TODO: call to an external class?
		Member m = new Member(member);
		Set<Task> t = getTasksFromCalendars(member);	// we're doing two things
		
		group.add(m);
		tasks.addAll(t);
	}
	
	private Set<Task> getTasksFromCalendars(String member) {
		// TODO: call to an external class?
		return new HashSet<>();
	}
	
	public void assignTask(String task, String member) {
		Task assignedTask = tasks.stream()
			    .filter(t -> t.getDescription().equals(task))
			    .findFirst()
			    .orElse(new Task("Null task"));	// FIXME: patrón Special Case / Null Object

		Member assignated =  group.stream()
			    .filter(m -> m.getName().equals(member))
			    .findFirst()
			    .orElse(new Member("Null member"));	// FIXME: patrón Special Case / Null Object
		
		this.taskAssigner.assignTask(assignedTask, assignated);
	}
}
