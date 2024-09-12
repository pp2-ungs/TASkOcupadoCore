package core;

public class Main {
	
	public static void main(String[] args) {
		TaskAssigner taskAssigner = new TaskAssigner();
		Task t = new Task("Afiliarse a la Cámpora");
		Member m = new Member("Gonza");
		taskAssigner.assignTask(t, m);
	}

}
