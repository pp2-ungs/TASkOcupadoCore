package views;

import javax.swing.JFrame;
//import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import controllers.TASkOcupadoController;
import core.TASkOcupado;
import core.TaskAssigner;
import obs.Observer;

@SuppressWarnings({ "serial" })
public class TASkOcupadoView extends JFrame implements Observer {
	
	private TASkOcupado taskOcupado;
	private TaskAssigner taskAssigner;
	private TASkOcupadoController controller;
	
	public TASkOcupadoView(TASkOcupado t) {
		taskOcupado = t;
		taskAssigner = t.getTaskAssigner();
		taskAssigner.addObserver(this);
		controller = new TASkOcupadoController(taskOcupado, this);
		
		init();
	}
	
	private void init() {
		//FlatMacDarkLaf.setup();
		
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
		new TASkOcupadoView(new TASkOcupado());
	}

	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub
	}
}
