package core;

import java.util.Observer;

@SuppressWarnings("deprecation")
public interface Observable {

	public void addObserver(Observer observer);
	public void removeObserver(Observer observer);
	
	// TODO: falta el update(), pero no lo pongo todavía porque hay que
	// resolver cómo se llamaría en TaskAssigner
	
	// Acá iría hasChanged y toda la cosa que tiene Observable
	// pero no sé si los vamos a necesitar, depende de cómo lo
	// implementemos. Entiendo que no hay que hacerlo a rajatabla
	// como lo dice Java ;)
	
}
