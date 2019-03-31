package swing;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.SwingController;

import javax.swing.JLabel;

public class LoginView extends JPanel {

	SwingController controller = SwingController.getInstance();
	private JTextField name;
	private JPasswordField password;
	private JButton loginButton;
	
	public LoginView() {

		setBounds(1000, 100, 900, 600);
		setBackground(Color.ORANGE);
		setLayout(null);
		
		name = new JTextField();
		name.setBounds(196, 255, 479, 40);
		add(name);
		name.setColumns(10);
		name.setText("admin");
		
		password = new JPasswordField();
		password.setBounds(196, 324, 479, 40);
		add(password);
		password.setText("admin");
		
		loginButton = new JButton("Login");
		loginButton.setBounds(377, 441, 101, 23);
		add(loginButton);
		
		JLabel lblNewLabel = new JLabel("ARANGO DB APPLICATION");
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(366, 88, 138, 59);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setBounds(75, 268, 75, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setBounds(75, 337, 75, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Employee access");
		lblNewLabel_3.setBounds(387, 203, 138, 14);
		add(lblNewLabel_3);
		
	}

	public JTextField getsName() {
		return name;
	}

	public void setName(JTextField name) {
		this.name = name;
	}

	public JPasswordField getPassword() {
		return password;
	}

	public void setPassword(JPasswordField password) {
		this.password = password;
	}

	public JButton getLoginButton() {
		return loginButton;
	}

	public void setLoginButton(JButton loginButton) {
		this.loginButton = loginButton;
	}
}
