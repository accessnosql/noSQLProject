package swing;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import controller.SwingController;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 120, 829, 480);
		add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Last login for employee", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Urgent incidences", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Rank incidences", null, panel_2, null);
		
		
	}

}
