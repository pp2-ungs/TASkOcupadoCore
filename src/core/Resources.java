package core;

import java.util.Set;

public class Resources {
	
	private Set<Member> members;
	private Set<Task> tasks;
	
	public Resources() {
		// TODO: buscar las cosas
		// acá necesitamos una interface para hacer plugin?
		// y cambiar lo que leemos dado el entorno?
	}
	
	// TODO: hacer bien
	public Member getMember(String name) {
		return members.stream()
			    .filter(m -> m.getName().equals(name))
			    .findFirst()
			    .orElse(new Member("Null member"));	// FIXME: patrón Special Case / Null Object
	}
	
	public Task getTask(String taskDescription) {
		return tasks.stream()
			    .filter(t -> t.getDescription().equals(taskDescription))
			    .findFirst()
			    .orElse(new Task(taskDescription));
	}

}
