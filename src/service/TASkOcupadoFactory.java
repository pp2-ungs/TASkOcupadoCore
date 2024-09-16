package service;

import core.ConcreteNotificator;
import core.Notificator;
import core.TASkOcupado;
import core.TaskAssigner;

public class TASkOcupadoFactory {

	public static TASkOcupado create(String path) {
		//TaskAssigner taskAssigner = new TaskAssigner();
		//Notificator notificator = new ConcreteNotificator();
		//taskAssigner.addObserver(notificator);
		
		return new TASkOcupado(TaskAssignerFactory.create(path));
	}

}
