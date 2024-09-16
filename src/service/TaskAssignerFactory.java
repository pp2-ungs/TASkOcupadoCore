package service;

import java.util.Set;

import core.ConcreteNotificator;
import core.Notificator;
import core.TaskAssigner;

public class TaskAssignerFactory {

	public static TaskAssigner create(String path) {
		TaskAssigner taskAssigner = new TaskAssigner();
		//Set<Notificator> notificators = Discoverer.discover(path);
		//taskAssigner.addObserver(notificators);
		
		Notificator notificator = new ConcreteNotificator();
		taskAssigner.addObserver(notificator);
		return taskAssigner;
	}

}
