package views;

import javax.swing.JPanel;
import controllers.TASkOcupadoController;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;

@SuppressWarnings("serial")
public class TaskAssignerPanel extends JPanel {
	
	public TaskAssignerPanel(TASkOcupadoController controller) {
		setSize(800, 600);
		setLayout(null);
		
		JLabel lblTask = new JLabel("Task");
		lblTask.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTask.setBounds(296, 168, 200, 23);
		add(lblTask);
		
		JComboBox<String> tasks = new JComboBox<>();
		tasks.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tasks.setBounds(296, 202, 200, 30);
		tasks.addItem("Bañarse");
		tasks.addItem("Desafiliarse de la Cámpora");
		tasks.addItem("Ir a entrenar");
		tasks.addItem("Hacer un backup del documento");
		add(tasks);
		
		JLabel lblMember = new JLabel("Member");
		lblMember.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMember.setBounds(296, 243, 200, 23);
		add(lblMember);
		
		JComboBox<String> members = new JComboBox<>();
		members.setFont(new Font("Tahoma", Font.PLAIN, 14));
		members.setBounds(296, 277, 200, 30);
		members.addItem("Xime");
		members.addItem("Hernan");
		members.addItem("Gonza");
		add(members);
		
		JButton btnNewButton = new JButton("Assign");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				controller.assignTask(tasks.getSelectedItem().toString(), members.getSelectedItem().toString());
			}
			
		});
		btnNewButton.setBounds(296, 319, 200, 30);
		add(btnNewButton);
		
		
		
		
		
		
	}
}
