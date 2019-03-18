package swing;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.SwingController;
import javax.swing.JTabbedPane;

public class IncidencesView extends JPanel {

	SwingController controller = SwingController.getInstance();
	/**
	 * Create the panel.
	 */
	public IncidencesView() {
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
		button.setBounds(66, 48, 127, 48);
		add(button);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 122, 834, 478);
		add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Incidence by ID", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New incidence", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Incidence list", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Incidences from an employee", null, panel_3, null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Incidences to an employee", null, panel_4, null);
		
		
	}
}
