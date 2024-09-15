package core;

import java.util.HashSet;
import java.util.Observer;
import java.util.Set;

@SuppressWarnings("deprecation")
public class TASkOcupado implements core.Observable {
	
	private TaskAssigner taskAssigner;
	private Set<Member> group;
	private Set<Task> tasks;
	
	public TASkOcupado(TaskAssigner t) {
		taskAssigner = t;
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
	
	// FIXME: Si se usan Strings en los key, se repiten los keys en el map
	public void assignTask(String task, String member) {
		Task assignedTask = tasks.stream()
			    .filter(t -> t.getDescription().equals(task))
			    .findFirst()
			    .orElse(new Task(task));	// FIXME: patrón Special Case / Null Object
		
		Member assignated =  group.stream()
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
	
	// TODO: check, código repetido
	// preguntar si esto es válido, ya que no sé
	// si realmente hace falta que TASkOcupado
	// sea observable
	@Override
	public void addObserver(Observer observer) {
		taskAssigner.addObserver(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		taskAssigner.removeObserver(observer);
	}
}
