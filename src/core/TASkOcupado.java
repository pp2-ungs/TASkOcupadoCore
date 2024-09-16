package core;

import java.util.HashSet;
import java.util.Observer;
import java.util.Set;

@SuppressWarnings("deprecation")
public class TASkOcupado implements core.Observable {
	
	private TaskAssigner taskAssigner;
	private Set<Member> group;
	private Set<Task> tasks;
	private Set<Observer> observers;
	
	public TASkOcupado(TaskAssigner t) {
		taskAssigner = t;
		group = new HashSet<>();
		tasks = new HashSet<>();
		observers =  new HashSet<>();
	}
	
	/*/
	// TODO: preguntar
	public TASkOcupado(TaskAssigner taskAssigner, Set<Member> group, Set<Task> tasks) {
		this.taskAssigner = taskAssigner;
		this.group = group;
		this.tasks = tasks;
	}
	
	public void addMember(Member member) {
		group.add(member);
	}
	/*/

	public void addMember(String member) {
		// TODO: call to an external class?
		Member m = new Member(member);
		Set<Task> t = getTasksFromCalendars(member);	// we're doing two things
		
		group.add(m);
		tasks.addAll(t);
	}
	
	private Set<Task> getTasksFromCalendars(String member) {
		// TODO: call to an external class?
		// Set<Task> s = TaskSearcher.searchTasks(member); 
		return new HashSet<>();
	}
	//*/
	
	public void assignTask(String task, String member) {
		Task assignedTask = tasks.stream()
			    .filter(t -> t.getDescription().equals(task))
			    .findFirst()
			    .orElse(new Task(task));	// FIXME: patrón Special Case / Null Object
		
		Member assignated = group.stream()
			    .filter(m -> m.getName().equals(member))
			    .findFirst()
			    .orElse(new Member("Null member"));	// FIXME: patrón Special Case / Null Object
		
		taskAssigner.assignTask(assignedTask, assignated);
	}
	
	public void assignTask(Task t, Member m) {
		taskAssigner.assignTask(t, m);
	}

	public void debug() {
		taskAssigner.debug();
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
	
	// TODO: implement
	public Object getData() {
		// taskAssigner.getData();
		// + group + tasks
		return new Object();
	}
}
