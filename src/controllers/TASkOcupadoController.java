package controllers;

import java.util.Observable;
import java.util.Observer;

import core.TASkOcupado;
import core.TaskAssigner;
import views.TASkOcupadoView;

@SuppressWarnings("deprecation")
public class TASkOcupadoController implements Observer {
	
	private TASkOcupado taskOcupado; 
	private TaskAssigner taskAssigner; // FIXME: esto esta bien?
	private TASkOcupadoView view;	// view
	
	public TASkOcupadoController(TASkOcupado t, TASkOcupadoView v) {
		taskOcupado = t;
		view = v;
		
		//taskAssigner.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		//taskOcupado.getData();
		// view.hide(coso);
	}

	public void assignTask(String taskName, String memberName) {
        taskOcupado.assignTask(taskName, memberName);
    }
}
