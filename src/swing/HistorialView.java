package swing;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import controller.SwingController;
import model.EmployeeListModel;
import model.EventListModel;
import model.IncidenceListModel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HistorialView extends JPanel {

	private final EmployeeListModel eListModel = new EmployeeListModel();
	private final IncidenceListModel iListModel = new IncidenceListModel();
	private final EventListModel eventListModel = new EventListModel();
	private Integer index;
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
		button.setBounds(65, 62, 220, 48);
		add(button);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 120, 829, 480);
		add(tabbedPane);
		
		
		//TAB 1: find last login from an employee
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Last login for employee", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Find last login:");
		lblNewLabel.setBounds(63, 98, 84, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(420, 166, 334, 37);
		panel.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 155, 183, 235);
		panel.add(scrollPane);
		
		JList<?> list = new JList();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				index = list.locationToIndex(e.getPoint());
				lblNewLabel_2.setText(controller.getLastLogin(index));
			}
		});
		scrollPane.setViewportView(list);
		list.setModel(eListModel);
		eListModel.setEmployeesList(controller.getEmployees());
		
		
		JLabel lblNewLabel_1 = new JLabel("Last login:");
		lblNewLabel_1.setBounds(547, 98, 139, 27);
		panel.add(lblNewLabel_1);
		
		
		
		JButton btnNewButton = new JButton("DELETE");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(index != null) {
					controller.deleteEmployeeLogins(index);
				}
			}
		});
		btnNewButton.setBounds(446, 286, 240, 37);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Delete login history of that user:");
		lblNewLabel_3.setBounds(461, 252, 225, 14);
		panel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Urgent incidences", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Rank incidences", null, panel_2, null);
		panel_2.setLayout(null);
		
		
		// TAP 3 Ranking Urgent Events
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(70, 76, 668, 324);
		panel_2.add(scrollPane_1);
		
		//TODO
		//JList<String> rankingList = new JList(controller.getUrgentHistoryList());
		//panel.add(rankingList);
		
		/*
		scrollPane_1.setViewportView(rankingList);
		rankingList.setModel(eventListModel);
		System.out.println(" -----------------" + controller.getEmployees().size());
		eventListModel.setEventList(controller.getUrgentHistoryList());
		
		*/
	}
}
