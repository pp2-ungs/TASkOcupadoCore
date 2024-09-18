package core;

import obs.Observer;

public interface Notificator extends Observer {
	
	public void notify(Object msg);
	
}
