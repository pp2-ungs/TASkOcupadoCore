package views;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import controllers.TASkOcupadoController;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TaskAssignerPanel extends JPanel {
	
	public TaskAssignerPanel(TASkOcupadoController controller) {
		setSize(800, 600);
		setLayout(null);
		
		JLabel lblTask = new JLabel("Task");
		lblTask.setBounds(353, 157, 137, 14);
		add(lblTask);
		
		JTextArea textAreaTask = new JTextArea();
		textAreaTask.setBounds(267, 182, 223, 22);
		add(textAreaTask);
		
		JLabel lblMember = new JLabel("Member");
		lblMember.setBounds(353, 265, 137, 14);
		add(lblMember);
		
		JTextArea textAreaMember = new JTextArea();
		textAreaMember.setBounds(267, 290, 223, 22);
		add(textAreaMember);
		
		JButton btnNewButton = new JButton("Assign");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				controller.assignTask(textAreaTask.getText(), textAreaMember.getText());
			}
			
		});
		btnNewButton.setBounds(329, 323, 89, 23);
		add(btnNewButton);
	}
}
