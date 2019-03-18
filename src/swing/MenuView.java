package swing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.SwingController;
import swing.TableData1;
import javax.swing.JTabbedPane;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuView extends JPanel {

	SwingController controller = SwingController.getInstance();
	private JButton bt1, bt2, bt3,bt4, bt5;
	private JButton scores;
	private JButton exit;
	
	
	public MenuView() {
		
		setBounds(1000, 100, 900, 600);
		setBackground(Color.GREEN);
		setLayout(null);
		
		bt1 = new JButton("Employees");
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.switchToEmployeesView();
			}
		});
		bt1.setBounds(244, 115, 249, 69);
		add(bt1);
		
		bt2 = new JButton("Incidences");
		bt2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controller.switchToIncidencesView();
			}
		});
		
		bt2.setBounds(244, 234, 249, 69);
		add(bt2);
		
		
		bt3 = new JButton("Exit");
		bt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		bt3.setBounds(518, 504, 169, 36);
		add(bt3);
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setBounds(321, 38, 133, 36);
		add(lblNewLabel);
		
		bt4 = new JButton("Logout");
		bt4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.switchToLogin();
			}
		});
		bt4.setBounds(89, 504, 169, 36);
		add(bt4);
		
		bt5 = new JButton("Historial");
		bt5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.switchToHistorialView();
			}
		});
		bt5.setBounds(246, 346, 247, 78);
		add(bt5);
			
	}
	
	}


