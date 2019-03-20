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

import com.arangodb.ArangoCollection;
import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.ArangoDatabase;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.CollectionEntity;
import com.arangodb.entity.DocumentCreateEntity;
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
    
    // Metodo que modifica una incidencia, cambia la persona de destino
    public void updateIncidence(String emKey, String inKey) {
    	
    	/*ArangoDB arango = new ArangoDB.Builder().build();
    	ArangoDatabase db = arango.db("myDB");
    	ArangoCollection collection = db.collection("some-collection");

    	BaseDocument document = new BaseDocument();
    	document.addAttribute("hello", "world");
    	DocumentCreateEntity<BaseDocument> info = collection.insertDocument(document);

    	document.addAttribute("hello", "world2");
    	collection.updateDocument(info.getKey(), document, new DocumentUpdateOptions());

    	BaseDocument doc = collection.getDocument(info.getKey());
    	assertThat(doc.getAttribute("hello"), is("world2"));*/
    	
    	//final String query = "UPDATE " + inKey + " WITH {employeeDest:"+emKey+"} IN incidences";
    	//System.out.println(query);

    }
    
    // Metodo que recupera toda la lista de incidencias que se han puesto en la base de datos
    public ArrayList<Incidence> incidencesList() {
    	
    	ArrayList<Incidence> incidencesList = new ArrayList<Incidence>();
    	try {
			final String query = "FOR t IN incidences return t";
			final ArangoCursor<BaseDocument> cursor = arangoDB.db(DBNAME).query(query, BaseDocument.class);
			while(cursor.hasNext()) {
				BaseDocument b = cursor.next();
				System.out.println("id: " + b.getAttribute("id"));
				System.out.println("createdAt: " + b.getAttribute("createdAt"));
				System.out.println("comment: " + b.getAttribute("comment"));
				System.out.println("employeeDest: " + b.getAttribute("employeeDest"));
				System.out.println("level: " + b.getAttribute("level"));
				
			} // else user/pass wrong
			return incidencesList;
		} catch (final ArangoDBException e) {
			System.err.println("Failed to execute query. " + e.getMessage());
		}
    	
    	return null;
    }


	public ArangoDB getArangoDB() {
		return arangoDB;
	}


	public String getDBNAME() {
		return DBNAME;
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
    	final BaseDocument myObject = new BaseDocument();
		myObject.addAttribute("username", e.getName());
		myObject.addAttribute("password", e.getName());
	    arangoDB.db(DBNAME).collection("employees").updateDocument("myKey", e.getArangoKey());
	
    }
    
    /*
    FOR u IN users
    UPDATE u._key WITH { name: CONCAT(u.firstName, " ", u.lastName) } IN users */

    
    

    
    //Events methods
    public void insertEvent(Event e) {
    	final BaseDocument myObject = new BaseDocument();
		//myObject.setKey("myKey");
		myObject.addAttribute("event", e.getEventTipo());
		myObject.addAttribute("userKey", e.getEmployeeKey());
		myObject.addAttribute("dateTime", e.getDateTimeFormatted());
		try {
			arangoDB.db(DBNAME).collection("historial").insertDocument(myObject);
			System.out.println("Document created");
		} catch (final ArangoDBException e1) {
			System.err.println("Failed to create document. " + e1.getMessage());
		}
    }
    
}
    
    /*
    // Método para insertar un nuevo empleado.
    public void insertEmpleado(Empleado e);

    // Método para validar el login de un empleado.
    public boolean loginEmpleado(String user, String pass);

    // Método para modificar el perfil de un empleado.
    public void updateEmpleado(Empleado e);

    // Método para eliminar un empleado.
    public void removeEmpleado(Empleado e);

    // Obtener una Incidencia a partir de su Id.
    public Incidencia getIncidenciaById(int id);

    // Obtener una lista de todas las incidencias
    public List<Incidencia> selectAllIncidencias();

    // Insertar una incidencia a partir de un objeto incidencia
    public void insertIncidencia(Incidencia i);

    // Obtener la lista de incidencias con destino un determinado
    // empleado, a partir de un objeto empleado.
    public List<Incidencia> getIncidenciaByDestino(Empleado e);

    // Obtener la lista de incidencias con origen un determinado
    // empleado, a partir de un objeto empleado.
    public List<Incidencia> getIncidenciaByOrigen(Empleado e);

    
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
