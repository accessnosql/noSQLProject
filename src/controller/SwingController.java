package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import exceptions.EmployeeException;
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
import utils.IncidenceLevel;


public class SwingController {

	private Employee userLogged;
	
	 private MyView view;
	 private LoginView login;
	 private MenuView menu;
	 private EmployeesView employeesView;
	 private HistorialView historialView;
	 private IncidencesView incidencesView;
	 private ArangoDAO arangoDAO;
	 private List<Employee> employees;
	 private List<Incidence> incidences;
	 private List<Incidence> incidenceSearch;
	 private List<Event> incidenceRanking;
	 private List<Event> urgentIncidencesRanking;
	 
	 
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
	 
	
	 
	 
	                                    //LOGIC
	 //login
	 
	 public void login() {
		 try {
			 String name = login.getsName().getText();
			 String pass = login.getPassword().getText();
			 userLogged = arangoDAO.loginEmployee(name, pass);
			 if(userLogged == null) {
				 throw new EmployeeException(EmployeeException.WRONG_LOGIN);
			 }
			 loginEvent();
			 //System.out.println(userLogged.getName());
			 menu = new MenuView();
			 switchToMenu();
		 }catch(EmployeeException e) {
			 JOptionPane.showMessageDialog(null, e, "Info", JOptionPane.INFORMATION_MESSAGE);
		 }
		
	 }
	 
	 
	 //employees
	 
	 public void insertEmployee() {
		 try {
			 String name = employeesView.getTextField().getText();
			 String pass1 = employeesView.getTextField_1().getText();
			 String pass2 = employeesView.getTextField_2().getText();
			 
			 if(!pass1.equals(pass2)){
				 throw new EmployeeException(EmployeeException.WRONG_PASS_CHECK);
			 }
			 arangoDAO.insertEmployee(new Employee(name, pass1));
			 employeesView.getTextArea().setText("New employee added");
		
			 
		 }catch(EmployeeException e) {
			 JOptionPane.showMessageDialog(null, e, "Info", JOptionPane.INFORMATION_MESSAGE);
		 }
	 }
	 
	 public void updateEmployee() {
		 arangoDAO.updateEmployee(userLogged);
	 }
	 
	 public void deleteEmployee(int index) {
		 //TODO: al eliminar, ¿que pasa con las incidences? reasignar?
		 arangoDAO.deleteEmployee(employees.get(index));
	 }
	 
	 public String[] getEmployeeNames() {
		 employees = arangoDAO.getAllEmployees();
		 String[] employeeNames = new String[employees.size()];
		 if(!employees.isEmpty()) {
			 for(int i = 0; i < employees.size(); i++) {
				 employeeNames[i] = employees.get(i).getName();
			 }
		 }
		 return employeeNames;
	 }
	 
	 public List<Employee> getEmployees(){
		 employees = arangoDAO.getAllEmployees();
		 return employees;
	 }
	 
	 
	                                   //INCIDENCES
	 
	 public void createIncidence(Incidence incidence) {
		 incidence.setId(userLogged.getArangoKey());
		 incidence.setCreatedAt(LocalDateTime.now());
		 arangoDAO.createIncidence(incidence);
		 
		 if(incidence.getLevel().equals(IncidenceLevel.HIGH)) {
			 Event event = new Event(Events.USER_URGENT_INCIDENCE, userLogged.getArangoKey(), LocalDateTime.now());
			 arangoDAO.insertEvent(event);
		 }
	 }
	 
	 public List<Incidence> getIncidencesFromDAO() {
		 //TODO refresco de la JList al añadir nueva incidence
		 incidences = arangoDAO.incidencesList(); 
		 for(Incidence i: incidences) {
			 System.out.println(i.toString());
		 }
		 return incidences;
	 }
	 
	 public String getIncidenceFromDAO(int index) {
		 Incidence i = arangoDAO.getIncidenceByKey(incidences.get(index).getArangoKey());
		 return incidenceLabel(i);
	 }
	 
	 public String incidenceLabel(Incidence i) {
		 return "Created by: " + arangoDAO.getEmployeeByKey(i.getId()).getName() + "\n"+
				"Date: " + i.actualTimeString() + "\n"+
				"Receiver: " + arangoDAO.getEmployeeByKey(i.getEmployeeDest()).getName() +"\n"+
				"Level: " + i.getLevel().toString() + "\n"+
				"Subject:" + i.getComment();
	 }
	 
	 public String[] getIncidencesString() {
		 String[] iString = new String[incidences.size()];
		 for(int i = 0; i < incidences.size(); i++) {
			 System.out.println(incidences.get(i));
			 iString[i] = incidenceResume(incidences.get(i));
			 System.out.println(iString[i]);
		 }
		 return iString;
	 }
	 
	 public String incidenceResume(Incidence i) {
		 return "DATE: " + i.actualTimeString() + ".  CREATED: " + arangoDAO.getEmployeeByKey(i.getId()) + ".  TO: " +
	            arangoDAO.getEmployeeByKey(i.getEmployeeDest()) + ".  LEVEL: " + i.getLevel().toString() + 
	            ".  SUBJECT: " + i.getComment();
	 }
	 
	 public String[] incidenceSearchByOrig(int index) {
		 Employee e = employees.get(index);
		 incidenceSearch = arangoDAO.findIncidencesByOrigin(e);
		 if(incidenceSearch.isEmpty()) {
			 return new String[] {"No incidences sent"};
		 }
		 return getIncidencesSearchString();
	 }
	 
	 public String[] incidenceSearchByDest(int index) {
		 Employee e = employees.get(index);
		 incidenceSearch = arangoDAO.findIncidencesByDestin(e);
		 if(incidenceSearch.isEmpty()) {
			 return new String[] {"No incidences received"};
		 }
		 return getIncidencesSearchString();
	 }
	 
	 public String[] getIncidencesSearchString() {
		 String[] iString = new String[incidenceSearch.size()];
		 for(int i = 0; i < incidenceSearch.size(); i++) {
			 System.out.println(incidenceSearch.get(i));
			 iString[i] = incidenceResume(incidenceSearch.get(i));
			 System.out.println(iString[i]);
		 }
		 return iString;
	 }
	 
	                                   //HISTORIAL
	 public String getLastLogin(int index) {
		 List<Event> loginEvents = arangoDAO.getEventFromUserKey(employees.get(index).getArangoKey(), Events.USER_LOGIN);
		 if(loginEvents.isEmpty()) {
			 return "User " + employees.get(index).getName() + " hasn't logged yet";
		 }
		 int size = loginEvents.size() - 1;
		 return loginEvents.get(size).getDateTimeFormatted();
	 }
	 
	 public String deleteEmployeeLogins(Integer index) {
		 arangoDAO.deleteLoginEvents(employees.get(index).getArangoKey(), Events.USER_LOGIN);
		 return "Login historial deleted";
	 }
	 
	 public List<Event> getHistoryList(){
		 incidenceRanking = arangoDAO.getEventsList();
		 return incidenceRanking;
	 }
	 
	 public List<Event> getUrgentHistoryList(){
		 urgentIncidencesRanking = new ArrayList<>();
		 
		 for (Event event : arangoDAO.getEventsList()) {
			 if(event.getEventTipo().equals(Events.USER_URGENT_INCIDENCE)) {
				 urgentIncidencesRanking.add(event);
			 }
			
		}
		 return urgentIncidencesRanking;
	 }
	 
	 public void rankingEvents() {
		 for (int i = 0; i < getEmployees().size(); i++) {
			for (Event e : getUrgentHistoryList()) {
				// esta lista de usuarios no tienen key, tendria que traer una lista de user por key para ser conparadas ...
				//if(e.getEmployeeKey().equals(getEmployees().get(i).ge))
			}
		}
	 }
	 
	 
	 
	                                    //VIEWS
	 public void switchToLogin() {
		 login = new LoginView();
		 view.setView((JPanel) login);
		 userLogged = null;
		 login.getLoginButton().addActionListener(e -> login()); 
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

		public Employee getUserLogged() {
			return userLogged;
		}
		
	    
	 
	 
	 
	
}
