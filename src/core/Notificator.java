package core;

import java.util.Observer;

@SuppressWarnings("deprecation")
public abstract class Notificator implements Observer {
	
	// TODO: ask, clase abstracta o interface?
	public abstract void notify(Object msg);
	
}
