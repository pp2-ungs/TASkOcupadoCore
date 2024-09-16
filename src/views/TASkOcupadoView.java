package views;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import controllers.TASkOcupadoController;
import core.ConcreteNotificator;
import core.Notificator;
import core.TASkOcupado;
import core.TaskAssigner;

@SuppressWarnings("deprecation")
public class TASkOcupadoView extends JFrame implements Observer { // Hay que ver bien si es una herencia (Podría tener un JFrame como atributo)
	
	private TASkOcupado taskOcupado;
	private TASkOcupadoController controller;
	
	public TASkOcupadoView(TASkOcupado taskOcupado) {
		this.taskOcupado = taskOcupado;
		controller = new TASkOcupadoController(this.taskOcupado, this);
		init();
	}
	
	private void init() {
		setVisible(true);
		setSize(800, 600);
		setResizable(false);
		setTitle("TASkOcupado");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		TaskAssignerPanel taskAssignerPanel = new TaskAssignerPanel(controller);
		getContentPane().add(taskAssignerPanel);
		
		repaint();
	}

	public static void main(String[] args) {
		TaskAssigner taskAssigner = new TaskAssigner(); // Esto me parece que lo hace el taskOcupado
		Notificator notificator = new ConcreteNotificator();
		// TASkOcupado taskOcupado = TASkOcupadoFactory.create();
		
		// TODO: esto es raro, necesitamos una clase que construya,
		// o se lo pasamos a task ocupado y que lo pase a task assigner
		taskAssigner.addObserver(notificator); // Esto me parece que lo hace el taskOcupado
		
		TASkOcupado taskOcupado = new TASkOcupado(taskAssigner);
		TASkOcupadoView view = new TASkOcupadoView(taskOcupado);

		taskOcupado.addObserver(view);
	}

	@Override
	public void update(Observable o, Object arg) {
		// Object data = model.getData();
		// updateView(data);
	}
}
