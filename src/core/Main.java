package core;

import ext.SMSNotificator;

public class Main {

	// startdebugging
	public final static Task taskLoca = new Task("Bañarse");
	public final static Member xime = new Member("Ebertz");
	public final static Member gonza = new Member("López");
	public final static Member hdr = new Member("Rondelli");
	//stopdebugging

	public static void main(String[] args) {

		Notificator notificator = new SMSNotificator();
		
		TASkOcupado taskOcupado = new TASkOcupado();
		taskOcupado.getTaskAssigner().addObserver(notificator);
//		taskOcupado.addMember("Gonza");	// one task
//		taskOcupado.addMember("Hernán");	// more than one task
//		taskOcupado.addMember("Xime");	// without tasks
		
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
			
			taskOcupado.assignTask("Dormir", "Hernán"); // perdón?
			taskOcupado.assignTask("Hacer chistes malos", "Hernán"); // 😒
			taskOcupado.assignTask("Ir a entrenar", "Xime");
			taskOcupado.assignTask("Ir a entrenar", "Xime");
			taskOcupado.assignTask("Ir a entrenar", "Xime");
			taskOcupado.assignTask("Ir a entrenar", "Xime");
//			taskOcupado.assignTask(taskLoca, xime);
//			taskOcupado.assignTask(taskLoca, xime);
//			taskOcupado.assignTask(taskLoca, xime);
//			taskOcupado.assignTask(taskLoca, gonza);
//			taskOcupado.assignTask(taskLoca, hdr);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		taskOcupado.debug();
	}
}
