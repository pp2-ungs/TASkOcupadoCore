package core;

public class TASkOcupado {
	
	private TaskAssigner taskAssigner;
	private Resources resources;
	
	public TASkOcupado() {
		taskAssigner = new TaskAssigner();
		resources = new Resources();
	}
	
	public TaskAssigner getTaskAssigner() {
		return taskAssigner;
	}
	
	public String[] getMembers() {
		return (String[]) resources.getMembers();
	}
	
	public String[] getTasks() {
		return (String[]) resources.getTasks();
	}
	
	public void assignTask(String task, String member) {
		Task assignedTask = resources.getTask(task);
		Member assignated = resources.getMember(member);
		
		taskAssigner.assignTask(assignedTask, assignated);
	}

	public void debug() {
		taskAssigner.debug();
	}
}
