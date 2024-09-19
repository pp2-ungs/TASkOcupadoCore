package core;

public class Main {

	// startdebugging
	public final static Task taskLoca = new Task("Bañarse");
	public final static Member xime = new Member("Ebertz");
	public final static Member gonza = new Member("López");
	public final static Member hdr = new Member("Rondelli");
	//stopdebugging

	public static void main(String[] args) {
		TASkOcupado taskOcupado = new TASkOcupado();
		
		String[] members = taskOcupado.getMembers();
		String[] tasks = taskOcupado.getTasks();
		
		taskOcupado.assignTask(tasks[0], members[0]);
	}
}
