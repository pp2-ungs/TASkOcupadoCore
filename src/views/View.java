package views;

import java.util.Observable;
import java.util.Observer;

import core.ConcreteNotificator;
import core.Notificator;
import core.TASkOcupado;
import core.TaskAssigner;

@SuppressWarnings("deprecation")
public class View implements Observer {

	public static void main(String[] args) {
		TaskAssigner taskAssigner = new TaskAssigner();
		Notificator notificator = new ConcreteNotificator();
		
		// TODO: esto es raro, necesitamos una clase que construya,
		// o se lo pasamos a task ocupado y que lo pase a task assigner
		taskAssigner.addObserver(notificator);
		
		
		TASkOcupado taskOcupado = new TASkOcupado(taskAssigner);
		View view = new View();

		taskOcupado.addObserver(view);
		
		// view.addModel(taskOcupado);
		
		// Controller c = new Controller(taskOcupado, view);
		// view.addController(c);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// Object data = model.getData();
		// updateView(data);
	}

}
