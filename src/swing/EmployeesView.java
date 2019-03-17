package swing;

import java.awt.Color;

import javax.swing.JPanel;

import controller.SwingController;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class EmployeesView extends JPanel {

	SwingController controller = SwingController.getInstance();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextArea textArea;
	/**
	 * Create the panel.
	 */
	public EmployeesView() {
		setBounds(1000, 100, 900, 600);
		setBackground(Color.LIGHT_GRAY);
		
		JButton btnNewButton = new JButton("<< Return to menu");
		btnNewButton.setBounds(44, 31, 127, 48);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.switchToMenu();
			}
		});
		setLayout(null);
		add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 115, 830, 485);
		add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Create new employee", null, panel, null);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(98, 103, 254, 37);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(98, 188, 255, 37);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setBounds(199, 67, 69, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setBounds(199, 163, 98, 14);
		panel.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(97, 273, 255, 37);
		panel.add(textField_2);
		
		JLabel lblRepeatPassword = new JLabel("Repeat password:");
		lblRepeatPassword.setBounds(199, 248, 128, 14);
		panel.add(lblRepeatPassword);
		
		JButton btnNewButton_1 = new JButton("Create");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.insertEmployee();
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
			}
		});
		btnNewButton_1.setBounds(189, 352, 89, 23);
		panel.add(btnNewButton_1);
		
		textArea = new JTextArea();
		textArea.setBounds(464, 146, 247, 79);
		panel.add(textArea);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Update employee", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Delete employee", null, panel_2, null);
		
		JLabel lblNewLabel = new JLabel("Employees");
		lblNewLabel.setBounds(339, 48, 139, 14);
		add(lblNewLabel);
	}
	public JTextField getTextField() {
		return textField;
	}
	public JTextField getTextField_1() {
		return textField_1;
	}
	public JTextField getTextField_2() {
		return textField_2;
	}
	public JTextArea getTextArea() {
		return textArea;
	}
	
}
