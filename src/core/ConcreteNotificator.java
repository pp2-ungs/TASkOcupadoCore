package core;

import java.util.Observable;

public class ConcreteNotificator implements Notificator {

	@SuppressWarnings("deprecation")
	@Override
	public void update(Observable o, Object arg) {
		// TODO: Preguntar
		notify(arg);
	}
	
	@Override
	public void notify(Object msg) {
		// TODO: how's this different from the other one?
		System.out.println(msg);
	}
}
