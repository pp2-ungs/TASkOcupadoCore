package core;

import java.util.Observable;

public class ConcreteNotificator extends Notificator {

	@SuppressWarnings("deprecation")
	@Override
	public void update(Observable o, Object arg) {
		System.out.println(arg);
	}
	
	public void notify(Object msg) {
		// TODO: how's this different from the other one?
		System.out.println(msg);
	}
}
