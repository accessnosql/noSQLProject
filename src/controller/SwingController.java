package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


import model.Employee;
import model.Event;
import model.Incidence;
import persistence.ArangoDAO;
import swing.EmployeesView;
import swing.HistorialView;
import swing.IncidencesView;
import swing.LoginView;
import swing.MenuView;
import swing.MyView;
import swing.TableData1;
import utils.Events;


public class SwingController {

	private Employee userLogged;
	
	 private MyView view;
	 private LoginView login;
	 private MenuView menu;
	 private EmployeesView employeesView;
	 private HistorialView historialView;
	 private IncidencesView incidencesView;
	 private ArangoDAO arangoDAO;
	 
	 
	 private static final SwingController instance = new SwingController();
	 public static SwingController getInstance() { return instance; }
	    
	 //Controller inits the initial view (SplitView with JPanel left and Jpanel right)
	 public SwingController() {
	   login = new LoginView();
	   view = new MyView(login);
	   arangoDAO = new ArangoDAO();
	   initController();

	 }
	 
	 //Login View logic
	 
	 /**
	  * The listeners of the login view
	  */
	 public void initController() {
		login.getLoginButton().addActionListener(e -> login()); 
	}
	 
	 /**
	  * 
	  */
	 public void login() {
		
			 String name = login.getsName().getText();
			 String pass = login.getPassword().getText();
			 userLogged = arangoDAO.loginEmployee(name, pass);
			 loginEvent();
			 System.out.println(userLogged.getName());
			 menu = new MenuView();
			 switchToMenu();
		
	 }
	 
	                                    //VIEWS
	 public void switchToLogin() {
		 login = new LoginView();
		 view.setView((JPanel) login);
		 userLogged = null;
	 }
	 
	 public void switchToMenu()  {
		 view.setView((JPanel) menu);
	 }
	 
	 public void switchToEmployeesView() {
		 employeesView = new EmployeesView();
		 view.setView((JPanel) employeesView);
	 }
	 
	 public void switchToHistorialView() {
		 historialView = new HistorialView();
		 view.setView((JPanel) historialView);
	 }
	 
	 public void switchToIncidencesView() {
		 incidencesView = new IncidencesView();
		 view.setView((JPanel) incidencesView);
	 }
	 
	 
	 
	 // Utilities
	    public String actualTimeString() {
	    	LocalDateTime now = LocalDateTime.now();
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formatDateTime = now.format(formatter);
	        return formatDateTime;
	    }
	    
	    public void loginEvent() {
	    	Event event = new Event();
	    	event.setEventTipo(Events.USER_LOGIN);
	    	event.setEmployeeKey(userLogged.getArangoKey());
	    	event.setDateTime(LocalDateTime.now());
	    	arangoDAO.insertEvent(event);
	    }
	 
	 
	 
	
}
