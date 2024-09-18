package core;

import java.util.Observer;

@SuppressWarnings("deprecation")
public interface Notificator extends Observer {
	
	public void notify(Object msg);
	
}
