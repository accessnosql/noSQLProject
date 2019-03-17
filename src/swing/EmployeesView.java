package swing;

import java.awt.Color;

import javax.swing.JPanel;

import controller.SwingController;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmployeesView extends JPanel {

	SwingController controller = SwingController.getInstance();
	/**
	 * Create the panel.
	 */
	public EmployeesView() {
		setBounds(1000, 100, 900, 600);
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		JButton btnNewButton = new JButton("<< Return to menu");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.switchToMenu();
			}
		});
		btnNewButton.setBounds(72, 61, 127, 48);
		add(btnNewButton);
	}

}
