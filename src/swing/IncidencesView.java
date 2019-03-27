package swing;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.SwingController;
import model.EmployeeListModel;
import model.Incidence;
import model.IncidenceListModel;
import utils.IncidenceLevel;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JPasswordField;

public class IncidencesView extends JPanel {

	private final EmployeeListModel eListModel = new EmployeeListModel();
	private final IncidenceListModel iListModel = new IncidenceListModel();
	private JList list_2;
	private JList list_4;
	SwingController controller = SwingController.getInstance();
	List<Incidence> incidences;
	List<Incidence> incidencesSearch;
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
		button.setBounds(66, 48, 209, 48);
		add(button);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 122, 834, 478);
		add(tabbedPane);
		
		incidences = controller.getIncidencesFromDAO();
		
		//NEW INCIDENCE, TAB 1
		incidence = new Incidence(); //creation of the new incidence to fill
		eListModel.setEmployeesList(controller.getEmployees());
		
		
		
		//TAB 2: INCIDENCE BY key, we show a list with all arango key associated
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Incidence by _Key", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(45, 107, 202, 310);
		panel_1.add(scrollPane_2);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(302, 197, 476, 220);
		panel_1.add(textArea);
		//JList listIncId = new JList(controller.getIncidencesId());
		JList<?> listIncId = new JList();
		listIncId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = listIncId.locationToIndex(e.getPoint());
				textArea.setText(controller.getIncidenceFromDAO(index));
				//System.out.println(controller.getIncidencesFromDAO().get(index));
			}
		});
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(596, 60, 99, 42);
		panel_1.add(lblNewLabel_4);
		
		listIncId.setModel(iListModel);
		iListModel.setIncidenceList(incidences);
		lblNewLabel_4.setText(Integer.toString(incidences.size()));
		scrollPane_2.setViewportView(listIncId);
		
		
		JLabel lblNewLabel = new JLabel("Actual incidences:");
		lblNewLabel.setBounds(45, 60, 202, 23);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Incidence result:");
		lblNewLabel_2.setBounds(460, 158, 137, 42);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("TOTAL INCIDENCES:");
		lblNewLabel_3.setBounds(460, 70, 126, 23);
		panel_1.add(lblNewLabel_3);
		
		
		
		//TAB 3: List of all incidences
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Incidence list", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Total:");
		lblNewLabel_1.setBounds(363, 37, 46, 14);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_5 = new JLabel(Integer.toString(incidences.size()));
		lblNewLabel_5.setBounds(419, 34, 46, 21);
		panel_2.add(lblNewLabel_5);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(27, 83, 767, 341);
		panel_2.add(scrollPane_3);
		
		JList<String> list = new JList(controller.getIncidencesString());
		scrollPane_3.setViewportView(list);
		
		
		//TAB 4: INCIDENCES FROM AN EMPLOYEE
		
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Incidences from an employee", null, panel_3, null);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(57, 115, 182, 275);
		panel_3.add(scrollPane_4);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(330, 120, 419, 264);
		panel_3.add(scrollPane_5);
		list_2 = new JList();
		scrollPane_5.setViewportView(list_2);
		
		JList<?> eo = new JList();
		eo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = eo.locationToIndex(arg0.getPoint());
				list_2 = new JList(controller.incidenceSearchByOrig(index));
				scrollPane_5.setViewportView(list_2);	
			}
		});
		
		scrollPane_4.setViewportView(eo);
		eo.setModel(eListModel);
		eListModel.setEmployeesList(controller.getEmployees());
		
		JLabel lblNewLabel_6 = new JLabel("Select SENDER:");
		lblNewLabel_6.setBounds(60, 59, 169, 23);
		panel_3.add(lblNewLabel_6);
		
		
		JLabel lblNewLabel_7 = new JLabel("Incidences:");
		lblNewLabel_7.setBounds(467, 63, 145, 14);
		panel_3.add(lblNewLabel_7);
		
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
				//System.out.println(controller.getEmployees().get(index).getArangoKey());
				incidence.setEmployeeDest(controller.getEmployees().get(index).getArangoKey());
			  }
		    });
		listEmpDest.setModel(eListModel);
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
		
		
		//TAB 5: INCIDENCES TO AN EMPLOYEE
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Incidences to an employee", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("Select RECEIVER:");
		lblNewLabel_8.setBounds(60, 59, 169, 23);
		panel_4.add(lblNewLabel_8);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(57, 115, 182, 275);
		panel_4.add(scrollPane_6);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(330, 120, 419, 264);
		panel_4.add(scrollPane_7);
		
		list_4 = new JList();
		scrollPane_7.setViewportView(list_4);
		
		JList<?> list_3 = new JList();
		list_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e0) {
				int index = list_3.locationToIndex(e0.getPoint());
				list_4 = new JList(controller.incidenceSearchByDest(index));
				scrollPane_7.setViewportView(list_4);	
			}
		});
		scrollPane_6.setViewportView(list_3);
		list_3.setModel(eListModel);
		eListModel.setEmployeesList(controller.getEmployees());
		
		JLabel lblNewLabel_9 = new JLabel("Incidences");
		lblNewLabel_9.setBounds(467, 63, 145, 14);
		panel_4.add(lblNewLabel_9);
		
		
		
		
		
		
	}
}
