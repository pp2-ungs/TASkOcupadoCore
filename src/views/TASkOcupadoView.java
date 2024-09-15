package views;

import java.lang.ModuleLayer.Controller;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import core.ConcreteNotificator;
import core.Notificator;
import core.TASkOcupado;
import core.TaskAssigner;
import javax.swing.JLabel;

@SuppressWarnings("deprecation")
public class TASkOcupadoView extends JFrame implements Observer { // Hay que ver bien si es una herencia (Podría tener un JFrame como atributo)
	
	private TASkOcupado taskOcupado;
	private Controller controller;
	
	public TASkOcupadoView() {
		setTitle("TASkOcupado");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 784, 561);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hola xd");
		lblNewLabel.setBounds(317, 260, 157, 53);
		panel.add(lblNewLabel);
	}

	public static void main(String[] args) {
		TaskAssigner taskAssigner = new TaskAssigner(); // Esto me parece que lo hace el taskOcupado
		Notificator notificator = new ConcreteNotificator();
		
		// TODO: esto es raro, necesitamos una clase que construya,
		// o se lo pasamos a task ocupado y que lo pase a task assigner
		taskAssigner.addObserver(notificator); // Esto me parece que lo hace el taskOcupado
		
		
		TASkOcupado taskOcupado = new TASkOcupado(taskAssigner);
		
		TASkOcupadoView view = new TASkOcupadoView();

		taskOcupado.addObserver(view);
		
		//view.addModel(taskOcupado);
		
		//Controller controller = new Controller(taskOcupado, view);
		//view.addController(c);
		// Se me hacen raros los add...() de la view
	}

	@Override
	public void update(Observable o, Object arg) {
		// Object data = model.getData();
		// updateView(data);
	}
}
