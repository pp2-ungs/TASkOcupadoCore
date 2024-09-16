package service;

import core.ConcreteNotificator;
import core.Notificator;
import core.TaskAssigner;

public class TaskAssignerFactory {

	public static TaskAssigner create(String path) {
		TaskAssigner taskAssigner = new TaskAssigner();
		// TODO: Esto es lo que tenemos que hacer
		//Set<Notificator> notificators = Discoverer.discover(path);
		//notificators.forEach(n -> taskAssigner.addObserver(n)); 
		
		// TODO: Esto tiene que volar
		Notificator notificator = new ConcreteNotificator();
		taskAssigner.addObserver(notificator);
		return taskAssigner;
	}

}
