/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import com.arangodb.ArangoDB;
//import com.arangodb.velocypack.module.jdk8.VPackJdk8Module;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.arangodb.ArangoCollection;
import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.ArangoDatabase;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.CollectionEntity;
import com.arangodb.entity.DocumentCreateEntity;
import com.arangodb.entity.DocumentEntity;
import com.arangodb.model.AqlQueryOptions;
import com.arangodb.model.DocumentUpdateOptions;
import com.arangodb.util.MapBuilder;
import com.arangodb.velocypack.VPackSlice;
import com.arangodb.velocypack.exception.VPackException;

import exceptions.EmployeeException;
//import com.arangodb.velocypack.module.jdk8.VPackJdk8Module;
import model.Employee;
import model.Event;
import model.Incidence;
import utils.Events;
import utils.IncidenceLevel;

// https://docs.arangodb.com/3.4/Drivers/Java/Reference/Collection/DocumentManipulation.html
/**
 *
 * @author alu2017454
 */
public class ArangoDAO {

	private String ip = Configuration.ip;
    private int port = Configuration.port;
    private String user = Configuration.user;
    private String pass = Configuration.pass;
    
    private final ArangoDB arangoDB;
    private final String DBNAME = "organization";

    public ArangoDAO() {
    	arangoDB = new ArangoDB.Builder().user(user).password(pass).build();
    }
    
    
    public void createDatabase() {
 			try {
 				arangoDB.createDatabase(DBNAME);
 				System.out.println("Database created: " + DBNAME);
 			} catch (final ArangoDBException e) {
 				System.err.println("Failed to create database: " + DBNAME + "; " + e.getMessage());
 			}
    }
    
    
   public void createCollection(String collectionName) {
 			try {
 				final CollectionEntity myArangoCollection = arangoDB.db(DBNAME).createCollection(collectionName);
 				System.out.println("Collection created: " + myArangoCollection.getName());
 			} catch (final ArangoDBException e) {
 				System.err.println("Failed to create collection: " + collectionName + "; " + e.getMessage());
 			}
    }
   
                                //EMPLOYEES
    
    public void insertEmployee(Employee e) {
			final BaseDocument myObject = new BaseDocument();
			//myObject.setKey("myKey");
			myObject.addAttribute("username", e.getName());
			myObject.addAttribute("password", e.getPass());
			try {
				arangoDB.db(DBNAME).collection("employees").insertDocument(myObject);
				System.out.println("Document created");
			} catch (final ArangoDBException e1) {
				System.err.println("Failed to create document. " + e1.getMessage());
			}
					
    }
    
    public Employee getEmployeeByKey(String arangoKey) {
    	//TODO da un error en consola de nullpointer exception, revisar
    	BaseDocument m = arangoDB.db(DBNAME).collection("employees").getDocument(arangoKey, BaseDocument.class);
    	Employee e = new Employee(m.getAttribute("username").toString());
		e.setArangoKey(m.getKey());
    	return e;
    }
    
    public List<Employee> getAllEmployees(){
    	List<Employee> employees = new ArrayList<>();
    	try {
			final String query = "FOR t IN employees return t";
			final ArangoCursor<BaseDocument> cursor = arangoDB.db(DBNAME).query(query, BaseDocument.class);
			while(cursor.hasNext()) {
				BaseDocument b = cursor.next();
				Employee e = new Employee(b.getAttribute("username").toString(), b.getAttribute("password").toString());
				e.setArangoKey(b.getKey());
				employees.add(e);
			  
			} 
			return employees;
		} catch (final ArangoDBException e) {
			System.err.println("Failed to execute query. " + e.getMessage());
		}
    	return employees;
    }
    
    public Employee loginEmployee(String name, String pass) {
    	Employee employee = null;
    	
    	// execute AQL queries  empleado
			try {
				final String query = "FOR t IN employees FILTER t.username == @username and t.password == @password RETURN t";
				final Map<String, Object> bindVars = new MapBuilder().put("username", name).get();
				bindVars.put("password", pass);
				final ArangoCursor<BaseDocument> cursor = arangoDB.db(DBNAME).query(query, bindVars, null,
					BaseDocument.class);
				if(cursor.hasNext()) {
					BaseDocument b = cursor.next();
					//System.out.println("Key: " + b.getKey());
					//System.out.println("name " + b.getAttribute("username"));
					//System.out.println("pass " + b.getAttribute("password"));
					Employee e = new Employee(b.getAttribute("username").toString(), b.getAttribute("password").toString());
					e.setArangoKey(b.getKey());
					return e;
				} 
				else {
					throw new EmployeeException(EmployeeException.WRONG_LOGIN);
				}
			} catch (Exception e) {
				
			}
			return employee; //TODO:
			
    }
   
    
    public void updateEmployee(Employee e) throws ArangoDBException {
    	BaseDocument m = arangoDB.db(DBNAME).collection("employees").getDocument(e.getArangoKey(), BaseDocument.class);
    	m.updateAttribute("username", e.getName());
    	m.updateAttribute("password", e.getPass());
    	arangoDB.db(DBNAME).collection("employees").updateDocument(e.getArangoKey(), m);
    }
    
   public void deleteEmployee(Employee e) throws ArangoDBException {
	   arangoDB.db(DBNAME).collection("employees").deleteDocument(e.getArangoKey());
   }

    
    

    
    
    
                                           //INCIDENCES
    
    
    // Metodo que crea una nueva incidencia en la base de datos
    public void createIncidence(Incidence i) {
    	final BaseDocument myObject = new BaseDocument();
    	myObject.addAttribute("id", i.getId());
    	myObject.addAttribute("createdAt", i.actualTimeString());
    	myObject.addAttribute("comment", i.getComment());
    	myObject.addAttribute("employeeDest", i.getEmployeeDest());
    	myObject.addAttribute("level", i.getLevel().toString());
    	try {
			arangoDB.db(DBNAME).collection("incidences").insertDocument(myObject);
			System.out.println("incidence send");
		} catch (final ArangoDBException e1) {
			System.err.println("Failed to create incidence . " + e1.getMessage());
		}
    }
    
    
    
    // Metodo que recupera toda la lista de incidencias que se han puesto en la base de datos
    public List<Incidence> incidencesList() {
    	List<Incidence> incidencesList = new ArrayList<Incidence>();
    	try {
			final String query = "FOR t IN incidences return t";
			final ArangoCursor<BaseDocument> cursor = arangoDB.db(DBNAME).query(query, BaseDocument.class);
			while(cursor.hasNext()) {
				BaseDocument b = cursor.next();
				incidencesList.add(incidenceConstructor(b));	
			} // else user/pass wrong
			return incidencesList;
		} catch (final ArangoDBException e) {
			System.err.println("Failed to execute query. " + e.getMessage());
		}
    	
    	return null;
    }
    
    public Incidence getIncidenceByKey(String arangoKey) {
    	BaseDocument m = arangoDB.db(DBNAME).collection("incidences").getDocument(arangoKey, BaseDocument.class);
    	Incidence i = new Incidence();
		i.setId(m.getAttribute("id").toString());
		i.setCreatedAtFromString(m.getAttribute("createdAt").toString());
	    i.setComment(m.getAttribute("comment").toString());
		i.setEmployeeDest(m.getAttribute("employeeDest").toString());
		i.setLevel(IncidenceLevel.getIncidenceByString(m.getAttribute("level").toString()));
		i.setArangoKey(m.getKey());
		
    	return i;
    }
    
    public List<Incidence> findIncidencesByOrigin(Employee e){
    	List<Incidence> incidences = new ArrayList<>();
    	try {
			final String query = "FOR t IN incidences FILTER t.id == @id RETURN t";
			final Map<String, Object> bindVars = new MapBuilder().put("id", e.getArangoKey()).get();
			final ArangoCursor<BaseDocument> cursor = arangoDB.db(DBNAME).query(query, bindVars, null,
				BaseDocument.class);
			while(cursor.hasNext()) {
				BaseDocument b = cursor.next();
				incidences.add(incidenceConstructor(b));	
			} 
		} catch (Exception e0) {
			JOptionPane.showMessageDialog(null, e0, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
      return incidences;
    }
    
    public List<Incidence> findIncidencesByDestin(Employee e){
    	List<Incidence> incidences = new ArrayList<>();
    	try {
			final String query = "FOR t IN incidences FILTER t.employeeDest == @employeeDest RETURN t";
			final Map<String, Object> bindVars = new MapBuilder().put("employeeDest", e.getArangoKey()).get();
			final ArangoCursor<BaseDocument> cursor = arangoDB.db(DBNAME).query(query, bindVars, null,
				BaseDocument.class);
			while(cursor.hasNext()) {
				BaseDocument b = cursor.next();
				incidences.add(incidenceConstructor(b));	
			} 
		} catch (Exception e0) {
			JOptionPane.showMessageDialog(null, e0, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
      return incidences;
    }
    
    public Incidence incidenceConstructor(BaseDocument m) {
    	Incidence i = new Incidence();
		i.setId(m.getAttribute("id").toString());
		i.setCreatedAtFromString(m.getAttribute("createdAt").toString());
	    i.setComment(m.getAttribute("comment").toString());
		i.setEmployeeDest(m.getAttribute("employeeDest").toString());
		i.setLevel(IncidenceLevel.getIncidenceByString(m.getAttribute("level").toString()));
		i.setArangoKey(m.getKey());
    	return i;
    }


	public ArangoDB getArangoDB() {
		return arangoDB;
	}


	public String getDBNAME() {
		return DBNAME;
	}
    
                                          //HISTORIAL
    
    //Events methods
    public void insertEvent(Event e) {
    	final BaseDocument myObject = new BaseDocument();
		//myObject.setKey("myKey");
		myObject.addAttribute("event", e.getEventTipo().toString());
		myObject.addAttribute("userKey", e.getEmployeeKey());
		myObject.addAttribute("dateTime", e.getDateTimeFormatted());
		try {
			arangoDB.db(DBNAME).collection("historial").insertDocument(myObject);
			System.out.println("Document created");
		} catch (final ArangoDBException e1) {
			System.err.println("Failed to create document. " + e1.getMessage());
		}
		System.out.println(e.getEmployeeKey());
    }
    
    public List<Event> getEventFromUserKey(String key, Events event){
    	List<Event> loginE = new ArrayList<>();
    	try {
			final String query = "FOR t IN historial FILTER t.userKey == @userKey and t.event == @event RETURN t";
			final Map<String, Object> bindVars = new MapBuilder().put("userKey", key).get();
			bindVars.put("event", event.toString());
			final ArangoCursor<BaseDocument> cursor = arangoDB.db(DBNAME).query(query, bindVars, null,
				BaseDocument.class);
			while(cursor.hasNext()) {
				BaseDocument b = cursor.next();
				loginE.add(eventConstructor(b));	
			} 
		} catch (Exception e0) {
			JOptionPane.showMessageDialog(null, e0, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
      return loginE;
    }
    
    public Event eventConstructor(BaseDocument b) {
    	Event e = new Event();
    	e.setDateTimeFromString(b.getAttribute("dateTime").toString());
    	e.setEmployeeKey(b.getAttribute("userKey").toString());
    	e.setEventTipo(Events.getEventByString(b.getAttribute("event").toString()));
    	e.setArangoKey(b.getKey());
    	return e;
    }
    
    //TODO error
    public void deleteLoginEvents(String key, Events event) {
    	try {
			final String query = "FOR t IN historial FILTER t.userKey == @userKey and t.event == @event RETURN t";
			final Map<String, Object> bindVars = new MapBuilder().put("userKey", key).get();
			bindVars.put("event", event.toString());
			final ArangoCursor<BaseDocument> cursor = arangoDB.db(DBNAME).query(query, bindVars, null,
				BaseDocument.class);
			while(cursor.hasNext()) {
				BaseDocument b = cursor.next();
				arangoDB.db(DBNAME).collection("historial").deleteDocument(b.getId());	
			} 
		} catch (Exception e0) {
			JOptionPane.showMessageDialog(null, e0, "Info", JOptionPane.INFORMATION_MESSAGE);
       }
    
    }
}
    
    /*
    
    
    // Método para insertar un evento en la tabla historial.
    // Pasaremos como parámetro un objeto tipo evento, y no devolverá nada.
    // Llamaremos a este método desde los métodos
    // que producen los eventos, que son 3:
    // 1) Cuando un usuario hace login 
    // 2) Cuando un usuario crea una incidencia de tipo urgente 
    // 3) Cuando se consultan las incidencias destinadas a un usuario 
    public void insertarEvento(Evento e);
    
    // Obtener la fecha-hora del último inicio de sesión para un empleado.
    public Evento getUltimoInicioSesion(Empleado e);

    // Obtener el ranking de los empleados por cantidad de incidencias
    // urgentes creadas (más incidencias urgentes primero).
    public List<RankingTO> getRankingEmpleados();
    


} */
