/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import com.arangodb.ArangoDB;
//import com.arangodb.velocypack.module.jdk8.VPackJdk8Module;

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
//import com.arangodb.velocypack.module.jdk8.VPackJdk8Module;
import model.Employee;

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


	public ArangoDB getArangoDB() {
		return arangoDB;
	}


	public String getDBNAME() {
		return DBNAME;
	}
    
    public Employee doEmployeeLogin(String name, String pass) {
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
					System.out.println("Key: " + b.getKey());
					System.out.println("name " + b.getAttribute("username"));
					System.out.println("pass " + b.getAttribute("password"));
				} // else user/pass wrong
				
			} catch (final ArangoDBException e) {
				System.err.println("Failed to execute query. " + e.getMessage());
			}
    	return employee;
    }
    
 

   

}
