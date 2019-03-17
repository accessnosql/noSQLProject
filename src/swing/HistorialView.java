package swing;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import controller.SwingController;

import javax.swing.JButton;
import javax.swing.JLabel;

public class HistorialView extends JPanel {

	SwingController controller = SwingController.getInstance();
	/**
	 * Create the panel.
	 */
	public HistorialView() {
		setBounds(1000, 100, 900, 600);
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		JButton button = new JButton("<< Return to menu");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.switchToMenu();
			}
		});
		button.setBounds(65, 62, 127, 48);
		add(button);
		
		
	}

}
