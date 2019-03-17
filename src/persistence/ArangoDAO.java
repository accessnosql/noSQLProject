/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import com.arangodb.ArangoDB;
//import com.arangodb.velocypack.module.jdk8.VPackJdk8Module;

import java.util.ArrayList;
import java.util.Map;

import com.arangodb.ArangoCollection;
import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.CollectionEntity;
import com.arangodb.model.AqlQueryOptions;
import com.arangodb.util.MapBuilder;
import com.arangodb.velocypack.VPackSlice;
import com.arangodb.velocypack.exception.VPackException;

import exceptions.EmployeeException;
//import com.arangodb.velocypack.module.jdk8.VPackJdk8Module;
import model.Employee;
import model.Event;
import model.Incidence;

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
    
    
    // Metodo que crea una nueva incidencia en la base de datos
    public void createIncidence(Incidence i) {
    	final BaseDocument myObject = new BaseDocument();
    	myObject.addAttribute("id", i.getId());
    	myObject.addAttribute("createdAt", i.getCreatedAt());
    	myObject.addAttribute("comment", i.getComment());
    	myObject.addAttribute("name", i.getName());
    	try {
			arangoDB.db(DBNAME).collection("incidences").insertDocument(myObject);
			System.out.println("incidence send");
		} catch (final ArangoDBException e1) {
			System.err.println("Failed to create incidence . " + e1.getMessage());
		}
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
				System.out.println("name: " + b.getAttribute("name"));
				System.out.println("comment " + b.getAttribute("comment"));
				System.out.println("createdAt " + b.getAttribute("createdAt") );
				incidencesList.add(new Incidence(b.getAttribute("id").toString(), b.getAttribute("createdAt").toString(), b.getAttribute("name").toString(),b.getAttribute("comment").toString()));
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
    
    /**
     * Called from doEmployeeLogin, adds last login date time
     * @param date
     */
    public void doEmployeeLoginDate(String date) {
    	
    }
    
    public void updateEmployee(Employee e) throws ArangoDBException {
    	final BaseDocument myObject = new BaseDocument();
		myObject.addAttribute("username", e.getName());
		myObject.addAttribute("password", e.getName());
	    arangoDB.db(DBNAME).collection("employees").updateDocument("myKey", e.getArangoKey());
	
    }
    
    

    
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
    // M�todo para insertar un nuevo empleado.
    public void insertEmpleado(Empleado e);

    // M�todo para validar el login de un empleado.
    public boolean loginEmpleado(String user, String pass);

    // M�todo para modificar el perfil de un empleado.
    public void updateEmpleado(Empleado e);

    // M�todo para eliminar un empleado.
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

    
    // M�todo para insertar un evento en la tabla historial.
    // Pasaremos como par�metro un objeto tipo evento, y no devolver� nada.
    // Llamaremos a este m�todo desde los m�todos
    // que producen los eventos, que son 3:
    // 1) Cuando un usuario hace login 
    // 2) Cuando un usuario crea una incidencia de tipo urgente 
    // 3) Cuando se consultan las incidencias destinadas a un usuario 
    public void insertarEvento(Evento e);
    
    // Obtener la fecha-hora del �ltimo inicio de sesi�n para un empleado.
    public Evento getUltimoInicioSesion(Empleado e);

    // Obtener el ranking de los empleados por cantidad de incidencias
    // urgentes creadas (m�s incidencias urgentes primero).
    public List<RankingTO> getRankingEmpleados();
    


} */
