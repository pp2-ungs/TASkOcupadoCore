package core;

public class Main {
	
	public static void main(String[] args) {
		TaskAssigner taskAssigner = new TaskAssigner();
		Notificator notificator = new ConcreteNotificator();
		
		// TODO: what are we going to do about this?
		// it seems like we need to instantiate TaskAssigner before
		// TASkOcupado, so we can build it with it's Observers
		taskAssigner.addObserver(notificator);
		
		TASkOcupado taskOcupado = new TASkOcupado(taskAssigner);
		taskOcupado.addMember("Gonza");	// one task
		taskOcupado.addMember("Hernán");	// more than one task
		taskOcupado.addMember("Xime");	// without tasks
		
		// init app:
		// taskOcupado.obtainMembersFromCalendar();
		// taskOcupado.obtainTasksFromCalendar();
		
		// GUI:
		
		try {
			int milliseconds = 400;
			
			taskOcupado.assignTask("Desafiliarse de la Cámpora", "Gonza");
			Thread.sleep(milliseconds);
	
			taskOcupado.assignTask("Ir a entrenar", "Hernán");
			Thread.sleep(milliseconds + 200);
			
			taskOcupado.assignTask("Comer cada 36 horas", "Hernán");
			Thread.sleep(milliseconds);
			
			taskOcupado.assignTask("Dormir", "Hernán");
			taskOcupado.assignTask("Hacer chistes malos", "Hernán");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
