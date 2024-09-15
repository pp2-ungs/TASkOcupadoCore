package controllers;

import java.util.Observable;
import java.util.Observer;

import core.TASkOcupado;
import views.View;

@SuppressWarnings("deprecation")
public class TASkOcupadoController implements Observer {
	
	TASkOcupado taskOcupado; // model
	View view;	// view
	
	public TASkOcupadoController(TASkOcupado t, View v) {
		taskOcupado = t;
		view = v;
		
		taskOcupado.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		taskOcupado.getData();
		// view.hide(coso);
	}
	
	// TODO: method handleEvent
}
