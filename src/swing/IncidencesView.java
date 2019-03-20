package swing;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.SwingController;
import model.EmployeeListModel;
import model.Incidence;
import utils.IncidenceLevel;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class IncidencesView extends JPanel {

	private final EmployeeListModel eListModel = new EmployeeListModel();
	SwingController controller = SwingController.getInstance();
	Incidence incidence;
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
		incidence = new Incidence(); //creation of the new incidence to fill
		
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
		listLevelInc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String selectedItem = (String) listLevelInc.getSelectedValue();
				System.out.println((String) listLevelInc.getSelectedValue());
				incidence.setLevel(IncidenceLevel.getIncidenceByInt(listLevelInc.locationToIndex(e.getPoint())));
			  }
		    });
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(308, 118, 220, 270);
		panel.add(scrollPane_1);
		
		JList<?> listEmpDest = new JList();
		listEmpDest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//int index = listEmpDest.locationToIndex(e.getPoint());
				//String selectedItem = (String) listEmpDest.getSelectedValue().get;
				int index = listEmpDest.locationToIndex(e.getPoint());
				//System.out.println(listEmpDest.locationToIndex(e.getPoint()));
				System.out.println(controller.getEmployees().get(index).getArangoKey());
				incidence.setEmployeeDest(controller.getEmployees().get(index).getArangoKey());
			  }
		    });
		listEmpDest.setModel(eListModel);
		eListModel.setEmployeesList(controller.getEmployees());
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
					incidence.setComment(t1TextIncidence.getText());
					controller.createIncidence(incidence);
					result.setText("New incidence added");
				}
			}
		});
		createInc.setBounds(602, 115, 121, 46);
		panel.add(createInc);
		
		
		
		//TAB 2: INCIDENCE BY id
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Incidence by id", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(45, 107, 202, 310);
		panel_1.add(scrollPane_2);
		
		//JList list = new JList(controller.getIncidencesId());
		JList list = new JList();
		scrollPane_2.setViewportView(list);
		
		JLabel lblNewLabel = new JLabel("Incidence search by  ID:");
		lblNewLabel.setBounds(45, 60, 202, 23);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Incidence result:");
		lblNewLabel_2.setBounds(375, 195, 296, 58);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("TOTAL INCIDENCES:");
		lblNewLabel_3.setBounds(460, 70, 126, 23);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(493, 93, 46, 42);
		panel_1.add(lblNewLabel_4);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Incidence list", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Incidences from an employee", null, panel_3, null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Incidences to an employee", null, panel_4, null);
		
		
	}
}
