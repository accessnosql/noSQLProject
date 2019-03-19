package swing;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.SwingController;
import utils.IncidenceLevel;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JList;

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
		
		
		//NEW INCIDENCE, TAB 1
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New incidence:", null, panel, null);
		panel.setLayout(null);
		
		JLabel l1 = new JLabel("Incidence message:");
		l1.setBounds(43, 65, 220, 22);
		panel.add(l1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 113, 220, 102);
		panel.add(scrollPane);
		
		JTextArea t1TextIncidence = new JTextArea();
		scrollPane.setViewportView(t1TextIncidence);
		
		JLabel l2 = new JLabel("Level:");
		l2.setBounds(45, 256, 129, 22);
		panel.add(l2);
		
		JList listLevelInc = new JList(IncidenceLevel.getIncidenceLevels());
		listLevelInc.setBounds(43, 289, 141, 106);
		panel.add(listLevelInc);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(308, 118, 220, 270);
		panel.add(scrollPane_1);
		
		JList listEmpDest = new JList();
		scrollPane_1.setViewportView(listEmpDest);
		
		JLabel l3 = new JLabel("Employee destination:");
		l3.setBounds(305, 69, 180, 18);
		panel.add(l3);
		
		JTextArea result = new JTextArea();
		result.setBounds(552, 181, 245, 207);
		panel.add(result);
		
		JButton createInc = new JButton("Create");
		createInc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if("".equals(t1TextIncidence.getText())){
			        result.setText("Please, enter a message");
		        }
				else {
					//controller.
				}
			}
		});
		createInc.setBounds(602, 115, 121, 46);
		panel.add(createInc);
		
		
		
		
		//TAB 2: INCIDENCE BY id
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Incidence by id", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Incidence list", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Incidences from an employee", null, panel_3, null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Incidences to an employee", null, panel_4, null);
		
		
	}
}
