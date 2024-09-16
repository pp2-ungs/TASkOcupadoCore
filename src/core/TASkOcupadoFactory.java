package core;

public class TASkOcupadoFactory {

	public static TASkOcupado create() {
		TaskAssigner taskAssigner = new TaskAssigner();
		Notificator notificator = new ConcreteNotificator();
		taskAssigner.addObserver(notificator);
		
		return new TASkOcupado(taskAssigner);
	}

}
