package core;

import java.util.Observer;

@SuppressWarnings("deprecation")
public interface Observable {

	// TODO: nuestra implementación de Observer
	public void addObserver(Observer observer);
	public void removeObserver(Observer observer);
	// public void notifyObservers(Object obj);
	// public Object getData();
	
	// Acá iría hasChanged y toda la cosa que tiene Observable
	// pero no sé si los vamos a necesitar, depende de cómo lo
	// implementemos. Entiendo que no hay que hacerlo a rajatabla
	// como lo dice Java ;)
	
}
