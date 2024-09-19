package core;

import java.util.Set;

import plugin.DataLoader;
import plugin.PluginFactory;

public class Resources {
	
	private Set<Member> members;
	private Set<Task> tasks;
	
	public Resources() {
		DataLoader data = PluginFactory.loadInstances();
		tasks = data.loadTasks();
		members = data.loadMembers();
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
	
	public Object[] getMembers() {
		return members.toArray();
	}
	
	public Object[] getTasks() {
		return tasks.toArray();
	}

}
