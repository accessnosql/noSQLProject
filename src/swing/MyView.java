package swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import swing.LoginView;
import swing.MenuView;
import swing.MyFrame;
import swing.MyPanel;

public class MyView {

	private MyFrame frame;
	private MyPanel panel;
	

	/**
	 * Launch the application.
	 */
	public MyView(LoginView login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Instantiation of MyFrame
					frame = new MyFrame();
					frame.setVisible(true);
					
					//Instantiation of MyPanel, add to frame
					//panel = new LoginView();
					//panel.setVisible(true);
					frame.getContentPane().add(login, BorderLayout.CENTER);
					panel.setLayout(null);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

   public void setView(JPanel j) {
	 frame.getContentPane().removeAll();
     frame.getContentPane().add(j);
     frame.validate();
     frame.repaint();
     frame.setVisible(true);	
	}
	
	
}
	
