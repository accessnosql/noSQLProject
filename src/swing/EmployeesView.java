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
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeesView extends JPanel {

	SwingController controller = SwingController.getInstance();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextArea textArea;
	private JTextField textField_3;
	private JTextField textField_4;
	
	private int index;
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
		
		//TAB 1: add employee
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
		
		
		//TAB 2: update employee
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Update employee", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Update name");
		lblNewLabel_3.setBounds(337, 54, 137, 14);
		panel_1.add(lblNewLabel_3);
		
		
		textField_3 = new JTextField();
		textField_3.setBounds(255, 94, 254, 33);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setText(controller.getUserLogged().getName());
		
		JLabel lblNewLabel_4 = new JLabel("Update password");
		lblNewLabel_4.setBounds(337, 159, 137, 14);
		panel_1.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setBounds(255, 211, 254, 41);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		textField_4.setText(controller.getUserLogged().getPass());
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(255, 395, 254, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(243, 376, 277, 33);
		panel_1.add(lblNewLabel_5);
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(textField_3.getText() == "" || textField_4.getText() == "") {
					lblNewLabel_5.setText("No null values allowed");
				}
				else {
					controller.getUserLogged().setName(textField_3.getText());
					controller.getUserLogged().setPass(textField_4.getText());
					controller.updateEmployee();
					lblNewLabel_5.setText("Employee updated");
					textField_3.setText(controller.getUserLogged().getName());
					textField_4.setText(controller.getUserLogged().getPass());
				}
			}
		});
		btnNewButton_2.setBounds(324, 306, 119, 23);
		panel_1.add(btnNewButton_2);
		
		
		
		//delete employee
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Delete employee", null, panel_2, null);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 91, 231, 342);
		panel_2.add(scrollPane);
		
		JLabel result2 = new JLabel("");
		result2.setBounds(416, 374, 258, 34);
		panel_2.add(result2);
		
		JList list = new JList(controller.getEmployeeNames());
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				index = list.locationToIndex(e.getPoint());
			}
		});
		scrollPane.setViewportView(list);
		JButton btnNewButton_3 = new JButton("Delete");
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.deleteEmployee(index);
				btnConfirm.setVisible(false);
				btnNewButton_3.setVisible(true);
				result2.setText("Employee deleted");
			}
		});
		
		JLabel lblNewLabel_6 = new JLabel("Select employee to delete:");
		lblNewLabel_6.setBounds(57, 42, 223, 27);
		panel_2.add(lblNewLabel_6);
		
		
		
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnNewButton_3.setVisible(false);
				btnConfirm.setVisible(true);
				
			}
		});
		btnNewButton_3.setBounds(438, 215, 236, 49);
		panel_2.add(btnNewButton_3);
		
		
		btnConfirm.setBounds(438, 314, 236, 49);
		panel_2.add(btnConfirm);
		
		
		btnConfirm.setVisible(false);
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
