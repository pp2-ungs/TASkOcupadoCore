package core;

public class TASkOcupado{
	
	private TaskAssigner taskAssigner;
	private Resources resources;
	
	// TODO: IMPORTANTE definir: hacemos un getTaskAssigner()
	// o le seteamos un task assigner?
	public TASkOcupado() {
		taskAssigner = new TaskAssigner();
		resources = new Resources();
	}
	
	public TaskAssigner getTaskAssigner() {
		return taskAssigner;
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
