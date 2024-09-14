package core;

public class Main {
	
	public static void main(String[] args) {
		TASkOcupado tasocupado = new TASkOcupado();
		Task t = new Task("Afiliarse a la Cámpora");
		Member m = new Member("Gonza");
		tasocupado.assignTaskToMember(t, m);
	}

}
